package com.jagakarur.nycschoolsdetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jagakarur.nycschoolsdetails.data.local.dao.SchoolDao
import com.jagakarur.nycschoolsdetails.domain.model.School


@Database(entities = [School::class], version = 1)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
}