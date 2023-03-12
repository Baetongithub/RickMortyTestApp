package com.example.rickmortytestapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortytestapp.data.local.model.RickMortyRemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(remoteKeys: List<RickMortyRemoteKeys>)

    @Query("DELETE FROM rickmorty_remote_keys")
    suspend fun deleteAllRemoteKeys()

    @Query("SELECT * FROM rickmorty_remote_keys WHERE id=:id")
    suspend fun getRemoteKeys(id: String): RickMortyRemoteKeys
}