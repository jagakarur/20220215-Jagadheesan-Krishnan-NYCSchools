package com.jagakarur.nycschoolsdetails.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagakarur.nycschoolsdetails.Constants.SCHOOL_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = SCHOOL_REMOTE_KEY_DATABASE_TABLE)
data class SchoolRemoteKey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dbn: String,
    val prevPage: String?,
    val nextPage: String?,
    val lastUpdated: Long?
)
