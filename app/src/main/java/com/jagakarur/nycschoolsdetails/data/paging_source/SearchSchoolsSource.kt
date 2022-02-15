package com.jagakarur.nycschoolsdetails.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.domain.model.School

class SearchSchoolsSource(
    private val nycSchoolApi: NycSchoolApi,
    private val query: String
) : PagingSource<Int, School>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, School> {
        return try {
            val schools = nycSchoolApi.searchSchools(query = searchQueryBuilder(query))
            if (schools.isNotEmpty()) {
                LoadResult.Page(
                    data = schools,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, School>): Int? {
        return state.anchorPosition
    }

    private fun searchQueryBuilder(query: String): String {
        return "lower(school_name) like lower(('%25$query%25'))"
    }
}
