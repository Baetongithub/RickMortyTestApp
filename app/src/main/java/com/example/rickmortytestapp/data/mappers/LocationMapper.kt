package com.example.rickmortytestapp.data.mappers

import com.example.rickmortytestapp.data.local.model.ResultLocationEntity
import com.example.rickmortytestapp.domain.model.location.ResultLocation

fun ResultLocation.toLocationEntity() = ResultLocationEntity(
    id,
    created,
    dimension,
    name,
    type,
    url
)

fun ResultLocationEntity.toLocation() = ResultLocation(
    id,
    created,
    dimension,
    name,
    type,
    url
)