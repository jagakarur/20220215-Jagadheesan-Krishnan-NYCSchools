package com.jagakarur.nycschoolsdetails.domain.use_cases.get_selected_school

import com.jagakarur.nycschoolsdetails.data.repository.Repository
import com.jagakarur.nycschoolsdetails.domain.model.School

class GetSelectedSchoolUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(dbn: String): School {
        return repository.getSelectedSchool(dbn = dbn)
    }
}