package com.example.myapplication

import com.example.myapplication.db.CheckEntity
import com.example.myapplication.db.ChecksDao
import com.example.myapplication.mappers.toCheckEntity
import com.example.myapplication.mappers.toResultUIModel
import com.example.myapplication.rest.IPVigilanteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

//reduce TTL for better user experience or increase TTL to reduce server load
private const val CACHE_TTL = 10000 //ms

class IpCheckRepository @Inject constructor(
    private val checksDao: ChecksDao,
    private val ipVigilanteService: IPVigilanteService
) {

    suspend fun getInfo(request: String): ResultUIModel = withContext(Dispatchers.IO) {
        val latestCached = findCached(request)
        return@withContext if (latestCached != null) {
            latestCached.toResultUIModel()
        } else {
            val info = ipVigilanteService.getInfo(request)
            val checkEntity = info.toCheckEntity(request)
            checksDao.save(checkEntity)
            checkEntity.toResultUIModel()
        }
    }

    suspend fun getLatestCached(): ResultUIModel? = withContext(Dispatchers.IO) {
        return@withContext checksDao.getLatest()?.toResultUIModel()
    }

    private suspend fun findCached(request: String): CheckEntity? {
        val latestCheck = checksDao.findByRequest(request).minByOrNull { it.createdAt }
        return if (latestCheck?.createdAt != null && Date().time - latestCheck.createdAt.time > CACHE_TTL) {
            latestCheck
        } else {
            null
        }
    }
}
