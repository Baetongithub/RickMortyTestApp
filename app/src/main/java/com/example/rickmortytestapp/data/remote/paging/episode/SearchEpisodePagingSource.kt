package com.example.rickmortytestapp.data.remote.paging.episode

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortytestapp.data.mappers.toEpisode
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode
import retrofit2.HttpException
import java.io.IOException

class SearchEpisodePagingSource(
    private val rickMortyAPI: RickMortyAPI,
    private val name: String
) : PagingSource<Int, ResultEpisode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultEpisode> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = rickMortyAPI.getEpisodes(name = name, page = page)

            val nextKey = if (response.resultEpisodes.size < params.loadSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            val resultCharacters = response.resultEpisodes.map { it.toEpisode() }
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

    override fun getRefreshKey(state: PagingState<Int, ResultEpisode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}