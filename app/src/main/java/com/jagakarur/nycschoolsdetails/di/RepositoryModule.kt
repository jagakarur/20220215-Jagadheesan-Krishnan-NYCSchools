package com.jagakarur.nycschoolsdetails.di

import android.content.Context
import com.jagakarur.nycschoolsdetails.data.repository.DataStoreOperationsImpl
import com.jagakarur.nycschoolsdetails.data.repository.Repository
import com.jagakarur.nycschoolsdetails.domain.repository.DataStoreOperations
import com.jagakarur.nycschoolsdetails.domain.use_cases.UseCases
import com.jagakarur.nycschoolsdetails.domain.use_cases.get_all_schools.GetAllSchoolsUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.get_selected_school.GetSelectedSchoolUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.get_sellected_school_score.GetSelectedSchoolScoreUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.search_schools.SearchSchoolsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        //dagger allow to use application context for this dependencies
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllSchoolsUseCase = GetAllSchoolsUseCase(repository),
            searchSchoolsUseCase = SearchSchoolsUseCase(repository),
            getSelectedSchoolUseCase = GetSelectedSchoolUseCase(repository),
            getSelectedSchoolScoreUseCase = GetSelectedSchoolScoreUseCase(repository),
        )
    }

}