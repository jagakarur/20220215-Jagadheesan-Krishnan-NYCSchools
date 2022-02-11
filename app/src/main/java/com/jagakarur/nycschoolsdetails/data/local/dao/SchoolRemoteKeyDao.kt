package com.jagakarur.nycschoolsdetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jagakarur.nycschoolsdetails.domain.model.SchoolRemoteKey


@Dao
interface SchoolRemoteKeyDao {
    @Query("SELECT * FROM school_remote_key_table WHERE dbn = :dbn")
    suspend fun getRemoteKeys(dbn: String): SchoolRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(schoolRemoteKeys: List<SchoolRemoteKey>)

    @Query("DELETE FROM school_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}