package com.jagakarur.nycschoolsdetails.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jagakarur.nycschoolsdetails.data.local.dao.SchoolDao
import com.jagakarur.nycschoolsdetails.data.local.dao.SchoolRemoteKeyDao
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolRemoteKey

@Database(entities = [School::class, SchoolRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class SchoolDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): SchoolDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, SchoolDatabase::class.java)
            } else {
                Room.databaseBuilder(context, SchoolDatabase::class.java, "schoolDatabase.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }


    abstract fun schoolDao(): SchoolDao
    abstract fun schoolRemoteKeyDAO(): SchoolRemoteKeyDao
}