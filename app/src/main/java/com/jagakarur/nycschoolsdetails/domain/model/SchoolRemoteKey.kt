package com.jagakarur.nycschoolsdetails.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagakarur.nycschoolsdetails.util.Constants.SCHOOL_REMOTE_KEYS_DATABASE_TABLE


@Entity(tableName = SCHOOL_REMOTE_KEYS_DATABASE_TABLE)
data class SchoolRemoteKey(
@PrimaryKey(autoGenerate = false)
val dbn: String, // technically mutable but fine for a demo
val nextPage: Int?,
val prevPage: Int?
)
