package com.jagakarur.nycschoolsdetails.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.jagakarur.nycschoolsdetails.util.Constants.SCHOOL_DATABASE_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
@Entity(tableName = SCHOOL_DATABASE_TABLE)
data class School(
    @PrimaryKey()
    @SerialName("dbn")
    val dbn: String,
    @SerialName("school_name")
    val schoolName: String?,
    @SerialName("school_email")
    val schoolEmail: String? = "No Email"
)

@Serializable
data class SchoolList(val errorMessage: String?, val schoolList: List<School?>?)