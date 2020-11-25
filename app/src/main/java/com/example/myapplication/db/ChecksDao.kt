package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChecksDao {
    @Query("SELECT * FROM check_entity WHERE :request = request")
    suspend fun findByRequest(request: String): List<CheckEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(checkEntity: CheckEntity)

    @Query("SELECT * FROM check_entity ORDER BY created_at DESC LIMIT 1")
    fun getLatest(): CheckEntity?
}