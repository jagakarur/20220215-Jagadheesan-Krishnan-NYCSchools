package com.jagakarur.nycschoolsdetails.domain.repository

import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore

interface LocalDataSource {
    suspend fun getSelectedSchool(dbn: String): School
}