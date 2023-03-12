package com.example.rickmortytestapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode

@Entity(tableName = "episode_entity")
data class ResultEpisodeEntity(

    @PrimaryKey
    val id: Int,
    val air_date: String,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
)