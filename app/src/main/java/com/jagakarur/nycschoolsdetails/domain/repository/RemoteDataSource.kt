package com.jagakarur.nycschoolsdetails.domain.repository

import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllSchools(): Flow<PagingData<School>>
    fun searchSchool(query: String): Flow<PagingData<School>>
    suspend fun getSelectedSchoolScore(dbn: String): List<SchoolScore>
}