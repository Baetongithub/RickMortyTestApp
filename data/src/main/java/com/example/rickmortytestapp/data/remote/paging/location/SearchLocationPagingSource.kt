package com.example.rickmortytestapp.data.remote.paging.location

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortytestapp.data.mappers.toLocation
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.location.ResultLocation
import retrofit2.HttpException
import java.io.IOException

class SearchLocationPagingSource(
    private val rickMortyAPI: RickMortyAPI,
    private val name: String
) : PagingSource<Int, ResultLocation>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultLocation> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response =
                rickMortyAPI.getLocation(name = name, page = page, pages = params.loadSize)

            val nextKey = if (response.results.size < params.loadSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            val resultCharacters = response.results.map { it.toLocation() }
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

    override fun getRefreshKey(state: PagingState<Int, ResultLocation>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}