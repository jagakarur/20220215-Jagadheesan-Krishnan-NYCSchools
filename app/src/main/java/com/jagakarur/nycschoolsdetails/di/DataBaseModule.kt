package com.jagakarur.nycschoolsdetails.di

import android.content.Context
import androidx.room.Room
import com.jagakarur.nycschoolsdetails.util.Constants.SCHOOL_DATABASE
import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.data.repository.LocalDataSourceImpl
import com.jagakarur.nycschoolsdetails.domain.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    //Going to have only one instance of database across app
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SchoolDatabase {
        return Room.databaseBuilder(
            context,
            SchoolDatabase::class.java,
            SCHOOL_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: SchoolDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            schoolDatabase = database
        )
    }


}