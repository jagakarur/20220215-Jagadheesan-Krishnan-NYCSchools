package com.jagakarur.nycschoolsdetails.domain.use_cases

import com.jagakarur.nycschoolsdetails.domain.use_cases.get_all_schools.GetAllSchoolsUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.save_onboarding.SaveOnBoardingUseCase


data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllSchoolsUseCase: GetAllSchoolsUseCase
)