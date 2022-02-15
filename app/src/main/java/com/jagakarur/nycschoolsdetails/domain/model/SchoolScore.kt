package com.jagakarur.nycschoolsdetails.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.jagakarur.nycschoolsdetails.util.Constants.SCHOOL_SCORE_DATABASE_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
@Entity(tableName = SCHOOL_SCORE_DATABASE_TABLE)
data class SchoolScore(
    @PrimaryKey()
    @SerialName("dbn")
    val dbn: String,
    @SerialName("school_name")
    val schoolName: String?,
    @SerialName("num_of_sat_test_takers")
    val satTestTakers: String? = null,
    @SerialName("sat_critical_reading_avg_score")
    val satReadAvgScore: String? = null,
    @SerialName("sat_math_avg_score")
    val satMathAvgScore: String? = null,
    @SerialName("sat_writing_avg_score")
    val satWriteAvgScore: String? = null,
)

//@Serializable
//data class SchoolList(val errorMessage: String?, val schoolList: List<School?>?)