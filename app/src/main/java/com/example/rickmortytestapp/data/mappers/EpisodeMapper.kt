package com.example.rickmortytestapp.data.mappers

import com.example.rickmortytestapp.data.local.model.ResultEpisodeEntity
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode

fun ResultEpisode.toEpisodeEntity() = ResultEpisodeEntity(
    id,
    air_date,
    created,
    episode,
    name,
    url
)

fun ResultEpisodeEntity.toEpisode() = ResultEpisode(
    id,
    air_date,
    created,
    episode,
    name,
    url
)