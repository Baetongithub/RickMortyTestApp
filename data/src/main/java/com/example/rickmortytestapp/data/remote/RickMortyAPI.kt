package com.example.rickmortytestapp.data.remote

import com.example.rickmortytestapp.data.remote.model.CharacterModel
import com.example.rickmortytestapp.data.remote.model.EpisodeModel
import com.example.rickmortytestapp.data.remote.model.LocationModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyAPI {

    @GET("api/character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("pages") pages: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("gender") gender: String? = null
    ): CharacterModel

    @GET("api/episode/")
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("pages") pages: Int? = null
    ): EpisodeModel

    @GET("api/location/")
    suspend fun getLocation(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("pages") pages: Int? = null
    ): LocationModel

}