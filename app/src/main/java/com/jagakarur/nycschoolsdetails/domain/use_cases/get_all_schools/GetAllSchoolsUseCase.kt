package com.jagakarur.nycschoolsdetails.domain.use_cases.get_all_schools

import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.data.repository.Repository
import com.jagakarur.nycschoolsdetails.domain.model.School
import kotlinx.coroutines.flow.Flow

class GetAllSchoolsUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<PagingData<School>>{
        return repository.getAllSchools()
    }
}