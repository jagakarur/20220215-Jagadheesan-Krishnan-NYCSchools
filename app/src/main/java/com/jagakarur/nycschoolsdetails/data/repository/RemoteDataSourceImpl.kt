package com.jagakarur.nycschoolsdetails.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.util.Constants.ITEMS_PER_PAGE
import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.data.paging_source.SchoolRemoteMediator
import com.jagakarur.nycschoolsdetails.data.paging_source.SearchSchoolsSource
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import com.jagakarur.nycschoolsdetails.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val nycSchoolApi: NycSchoolApi,
    private val schoolDatabase: SchoolDatabase
): RemoteDataSource {

    private val schoolDao = schoolDatabase.schoolDao()

    override fun getAllSchools(): Flow<PagingData<School>> {
        val pagingSourceFactory = { schoolDao.getAllSchools() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = SchoolRemoteMediator(
                nycSchoolApi = nycSchoolApi,
                schoolDatabase = schoolDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchSchool(query: String): Flow<PagingData<School>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchSchoolsSource(nycSchoolApi = nycSchoolApi, query = query)
            }
        ).flow
    }

    //TODO: Need to write school score details to local db
    override suspend fun getSelectedSchoolScore(dbn: String): List<SchoolScore> {
        return nycSchoolApi.getSchoolScore(dbn = dbn)
    }


}