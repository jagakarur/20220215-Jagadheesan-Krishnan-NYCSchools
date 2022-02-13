package com.jagakarur.nycschoolsdetails.data.remote

import com.jagakarur.nycschoolsdetails.domain.model.ApiResponse
import com.jagakarur.nycschoolsdetails.domain.model.School

import retrofit2.http.GET
import retrofit2.http.Query
interface  NycSchoolApi {
    @GET("/resource/s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("${'$'}offset") page: Int =0,
        @Query("${'$'}limit") limit: Int =3
    ): List<School>

    @GET("/resource/s3k6-pzi2.json")
    suspend fun searchSchools(
        @Query("name") name: String
    ): ApiResponse

}