package com.jagakarur.nycschoolsdetails.data.repository

import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.repository.DataStoreOperations
import com.jagakarur.nycschoolsdetails.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllSchools(): Flow<PagingData<School>> {
        return remoteDataSource.getAllSchools()
    }


    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}