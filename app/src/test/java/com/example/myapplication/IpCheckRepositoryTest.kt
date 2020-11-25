package com.example.myapplication

import com.example.myapplication.db.ChecksDao
import com.example.myapplication.rest.IPVigilanteService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class IpCheckRepositoryTest : BaseTest() {

    private val checksDao: ChecksDao = mockk(relaxed = true)
    private val ipVigilanteService: IPVigilanteService = mockk(relaxed = true)

    private lateinit var cut: IpCheckRepository

    @Before
    fun beforeTest() {
        cut = IpCheckRepository(checksDao, ipVigilanteService)
    }

    @Test
    fun `If no cached result then get response from server`() = runBlocking {
        //given
        val request = ""
        coEvery { checksDao.findByRequest(request) } returns listOf()
        coEvery { ipVigilanteService.getInfo(request) } returns mockk(relaxed = true)

        //when
        cut.getInfo(request)

        //then
        coVerify { ipVigilanteService.getInfo(request) }
    }
}