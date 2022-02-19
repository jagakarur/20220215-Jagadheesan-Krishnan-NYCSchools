package com.jagakarur.nycschoolsdetails.data.remote

import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore

class FakeNycSchoolApi : NycSchoolApi {

    private val schools = listOf(
        School(
            dbn = "x1",
            schoolName = "School1",
            schoolEmail = "Test@testemail.com",
            graduationRate = "0.6"
        ),
        School(
            dbn = "x2",
            schoolName = "School2",
            schoolEmail = "Test2@testemail.com",
            graduationRate = "0.9"
        ),
        School(
            dbn = "x3",
            schoolName = "School3",
            schoolEmail = "Test3@testemail.com",
            graduationRate = "0.2"
        )
    )

    private val schoolScore = listOf<SchoolScore>(
        SchoolScore(
            dbn = "x1",
            schoolName = "School1",
            satTestTakers = "",
            satMathAvgScore = "",
            satReadAvgScore = "",
            satWriteAvgScore = ""
        ),
        SchoolScore(
            dbn = "x2",
            schoolName = "School2",
            satTestTakers = "",
            satMathAvgScore = "",
            satReadAvgScore = "",
            satWriteAvgScore = ""
        ),
        SchoolScore(
            dbn = "x3",
            schoolName = "School3",
            satTestTakers = "",
            satMathAvgScore = "",
            satReadAvgScore = "",
            satWriteAvgScore = ""
        )
    )


    override suspend fun getAllSchools(offset: Int, limit: Int, order: String): List<School> {
        return schools
    }

    override suspend fun searchSchools(query: String): List<School> {
        return findSchools(query = query)
    }

    override suspend fun getSchoolScore(dbn: String): List<SchoolScore> {
        return schoolScore
    }

    private fun findSchools(query: String): List<School> {
        val founded = mutableListOf<School>()
        return if (query.isNotEmpty()) {
            schools.forEach { school ->
                if (school.schoolName?.let { it.lowercase().contains(queryStringRemoval(query.lowercase())) } == true) {
                    founded.add(school)
                }
            }
            founded
        } else {
            emptyList()
        }
    }

    private fun queryStringRemoval(query: String): String {
        return query.replace("lower(school_name) like lower(('%25", "").replace("%25'))", "")
    }
}