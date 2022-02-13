package com.jagakarur.nycschoolsdetails.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.domain.model.School
import javax.inject.Inject

@ExperimentalPagingApi
class SchoolRemoteMediator @Inject constructor(
    private val nycSchoolApi: NycSchoolApi,
    private val schoolDatabase: SchoolDatabase
) : RemoteMediator<Int, School>() {
    private val schoolDao = schoolDatabase.schoolDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, School>): MediatorResult {
        val position = state.anchorPosition ?: 1
        val offset = (if (state.anchorPosition != null) ((position - 1) * 10) + 1 else 1) - 1
        return try {
            val response = nycSchoolApi.getAllSchools(page = offset)
            if (true) {
                schoolDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        schoolDao.deleteAllSchools()
                    }
                    schoolDao.addSchools(schools = response)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)

        }

    }

}