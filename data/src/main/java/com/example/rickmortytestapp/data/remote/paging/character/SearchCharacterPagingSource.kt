package com.example.rickmortytestapp.data.remote.paging.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortytestapp.data.mappers.toCharacter
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.character.ResultCharacter
import retrofit2.HttpException
import java.io.IOException

class SearchCharacterPagingSource(
    private val rickMortyAPI: RickMortyAPI,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val gender: String?,
) : PagingSource<Int, ResultCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultCharacter> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = if (status == null || species == null || gender == null) {
                rickMortyAPI.getCharacters(name = name, page = page, pages = params.loadSize)
            } else {
                rickMortyAPI.getCharacters(
                    name = name,
                    status = status,
                    species = species,
                    gender = gender,
                    page = page,
                    pages = params.loadSize
                )
            }
            val nextKey = if (response.results.size < params.loadSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            val resultCharacters = response.results.map { it.toCharacter() }
            LoadResult.Page(
                data = resultCharacters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}