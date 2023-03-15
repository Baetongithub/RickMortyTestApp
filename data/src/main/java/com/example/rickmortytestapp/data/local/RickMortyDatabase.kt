package com.example.rickmortytestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickmortytestapp.data.local.dao.EpisodeDao
import com.example.rickmortytestapp.data.local.dao.LocationDao
import com.example.rickmortytestapp.data.local.dao.RemoteKeysDao
import com.example.rickmortytestapp.data.local.dao.RickMortyDao
import com.example.rickmortytestapp.data.local.model.ResultCharacterEntity
import com.example.rickmortytestapp.data.local.model.ResultEpisodeEntity
import com.example.rickmortytestapp.data.local.model.ResultLocationEntity
import com.example.rickmortytestapp.data.local.model.RickMortyRemoteKeys

@Database(
    entities = [
        RickMortyRemoteKeys::class,
        ResultCharacterEntity::class,
        ResultLocationEntity::class,
        ResultEpisodeEntity::class],
    version = 4,
    exportSchema = true
)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun getRemoteKeysDao(): RemoteKeysDao

    abstract fun getRickMortyDao(): RickMortyDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getEpisodeDao(): EpisodeDao
}