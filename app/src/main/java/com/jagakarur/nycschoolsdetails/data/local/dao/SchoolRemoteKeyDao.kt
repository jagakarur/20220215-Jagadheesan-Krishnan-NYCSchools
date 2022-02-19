package com.jagakarur.nycschoolsdetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jagakarur.nycschoolsdetails.domain.model.SchoolRemoteKey
@Dao
interface SchoolRemoteKeyDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(keys: List<SchoolRemoteKey>)

    @Query("SELECT * FROM school_remote_keys_table WHERE dbn = :dbn")
    suspend fun remoteKeysByDbn(dbn: String): SchoolRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(schoolRemoteKey: List<SchoolRemoteKey>)


    @Query("DELETE FROM school_remote_keys_table")
    suspend fun deleteAllRemoteKeys()



}