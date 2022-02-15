package com.jagakarur.nycschoolsdetails.domain.use_cases.search_schools

import androidx.paging.PagingData
import com.jagakarur.nycschoolsdetails.data.repository.Repository
import com.jagakarur.nycschoolsdetails.domain.model.School
import kotlinx.coroutines.flow.Flow

class SearchSchoolsUseCase (private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<School>> {
        return repository.searchSchools(query = query)
    }
}