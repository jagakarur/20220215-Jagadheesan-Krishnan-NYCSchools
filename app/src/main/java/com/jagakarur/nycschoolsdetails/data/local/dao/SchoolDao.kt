package com.jagakarur.nycschoolsdetails.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jagakarur.nycschoolsdetails.domain.model.School

@Dao
interface SchoolDao {

    @Query("SELECT * FROM school_table ORDER BY schoolName ASC")
    fun getAllSchools(): PagingSource<Int, School>

    @Query("SELECT * FROM school_table WHERE dbn=:dbn")
    fun getSelectedSchool(dbn: String): School

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSchools(schools: List<School>)

    @Query("DELETE FROM school_table")
    suspend fun deleteAllSchools()

}