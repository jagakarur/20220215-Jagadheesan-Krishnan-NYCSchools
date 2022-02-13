package com.jagakarur.nycschoolsdetails.domain.repository

import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.domain.model.School
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllSchools(): Flow<PagingData<School>>
    fun searchSchool(): Flow<PagingData<School>>
}