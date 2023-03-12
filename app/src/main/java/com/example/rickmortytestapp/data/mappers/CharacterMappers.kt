package com.example.rickmortytestapp.data.mappers

import com.example.rickmortytestapp.data.local.model.ResultCharacterEntity
import com.example.rickmortytestapp.domain.model.character.ResultCharacter

fun ResultCharacter.toCharacterEntity() = ResultCharacterEntity(
    id,
    created,
    gender,
    image,
    name,
    species,
    status,
    type,
    url
)

fun ResultCharacterEntity.toCharacter() = ResultCharacter(
    created,
    gender,
    id,
    image,
    name,
    species,
    status,
    type,
    url
)