package com.jagakarur.nycschoolsdetails.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagakarur.nycschoolsdetails.Constants.SCHOOL_DATABASE_TABLE

@Entity (tableName = SCHOOL_DATABASE_TABLE)
data class School (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dbn : String,
    val schoolName: String,
    val schoolEmail: String
)