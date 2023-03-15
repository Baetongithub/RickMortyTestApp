package com.example.rickmortytestapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_entity")
data class ResultLocationEntity(

    @PrimaryKey
    val id: Int,
    val created: String,
    val dimension: String,
    val name: String,
    val type: String,
    val url: String
)