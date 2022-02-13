package com.jagakarur.nycschoolsdetails.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnBoardingState(completed: Boolean)
    //asynchronous data stream
    fun readOnBoardingState(): Flow<Boolean>

}