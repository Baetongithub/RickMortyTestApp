package com.example.rickmortytestapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickmortytestapp.domain.model.location.ResultLocation

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