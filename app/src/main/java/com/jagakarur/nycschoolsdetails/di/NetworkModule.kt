package com.jagakarur.nycschoolsdetails.di

import androidx.paging.ExperimentalPagingApi
import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.data.repository.RemoteDataSourceImpl
import com.jagakarur.nycschoolsdetails.domain.repository.RemoteDataSource
import com.jagakarur.nycschoolsdetails.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
       val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true;}
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }



    @Provides
    @Singleton
    fun provideSchoolApi(retrofit: Retrofit): NycSchoolApi{
        return retrofit.create(NycSchoolApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        nycSchoolApi: NycSchoolApi,
        schoolDatabase: SchoolDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            nycSchoolApi = nycSchoolApi,
            schoolDatabase = schoolDatabase
        )
    }

}