package com.example.rickmortytestapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortytestapp.data.local.model.ResultEpisodeEntity

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisode(resultEpisodeEntity: List<ResultEpisodeEntity>)

    @Query("DELETE FROM episode_entity")
    suspend fun deleteAllEpisode()

    @Query("SELECT * FROM episode_entity")
    fun getAllEpisode(): PagingSource<Int, ResultEpisodeEntity>
}