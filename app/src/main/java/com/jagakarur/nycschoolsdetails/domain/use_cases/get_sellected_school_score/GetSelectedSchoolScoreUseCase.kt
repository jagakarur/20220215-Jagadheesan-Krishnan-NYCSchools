package com.jagakarur.nycschoolsdetails.domain.use_cases.get_sellected_school_score

import com.jagakarur.nycschoolsdetails.data.repository.Repository
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore

class GetSelectedSchoolScoreUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(dbn: String): List<SchoolScore> {
        return repository.getSelectedSchoolScore(dbn = dbn)
    }
}