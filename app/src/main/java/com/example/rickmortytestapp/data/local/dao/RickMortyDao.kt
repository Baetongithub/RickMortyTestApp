package com.example.rickmortytestapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortytestapp.data.local.model.ResultCharacterEntity

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(resultCharacterEntity: List<ResultCharacterEntity>)

    @Query("DELETE FROM result_entity")
    suspend fun deleteAllRickMorty()

    @Query("SELECT * FROM result_entity")
    fun getCharacters(): PagingSource<Int, ResultCharacterEntity>

}