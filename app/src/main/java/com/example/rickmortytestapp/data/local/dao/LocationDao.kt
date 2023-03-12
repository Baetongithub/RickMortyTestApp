package com.example.rickmortytestapp.data.local.dao

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortytestapp.data.local.model.ResultLocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(resultLocationEntity: List<ResultLocationEntity>)

    @Query("DELETE FROM location_entity")
    suspend fun deleteAllLocation()

    @Query("SELECT * FROM location_entity")
    fun getAllLocation(): PagingSource<Int, ResultLocationEntity>
}