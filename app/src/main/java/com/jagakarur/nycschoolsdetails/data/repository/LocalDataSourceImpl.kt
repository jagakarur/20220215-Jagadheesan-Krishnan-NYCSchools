package com.jagakarur.nycschoolsdetails.data.repository

import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    schoolDatabase: SchoolDatabase
) : LocalDataSource {
    private val schoolDao = schoolDatabase.schoolDao()
    override suspend fun getSelectedSchool(dbn: String): School {
        return schoolDao.getSelectedSchool(dbn = dbn)
    }
}