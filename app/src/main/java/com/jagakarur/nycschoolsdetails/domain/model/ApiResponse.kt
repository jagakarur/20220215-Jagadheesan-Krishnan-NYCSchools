package com.jagakarur.nycschoolsdetails.domain.model

import kotlinx.serialization.Serializable

/**
 * This class will help to fetch the response from api
 */

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val schools: List<School> = emptyList(),
    val lastUpdated: Long? = null
)