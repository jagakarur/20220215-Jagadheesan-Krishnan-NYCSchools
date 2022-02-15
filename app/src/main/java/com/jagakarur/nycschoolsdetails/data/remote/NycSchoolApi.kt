package com.jagakarur.nycschoolsdetails.data.remote

import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import retrofit2.http.GET
import retrofit2.http.Query

interface NycSchoolApi {
    @GET("/resource/s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("${'$'}offset") offset: Int,
        @Query("${'$'}limit") limit: Int = 10,
    ): List<School>

    @GET("/resource/s3k6-pzi2.json")
    suspend fun searchSchools(
        @Query("${'$'}where", encoded = true) query: String
    ): List<School>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun getSchoolScore(
        @Query("dbn", encoded = true) dbn: String
    ): List<SchoolScore>

}