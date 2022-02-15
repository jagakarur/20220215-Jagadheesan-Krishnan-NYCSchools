package com.jagakarur.nycschoolsdetails.domain.use_cases

import com.jagakarur.nycschoolsdetails.domain.use_cases.get_all_schools.GetAllSchoolsUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.get_selected_school.GetSelectedSchoolUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.get_sellected_school_score.GetSelectedSchoolScoreUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.jagakarur.nycschoolsdetails.domain.use_cases.search_schools.SearchSchoolsUseCase


data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllSchoolsUseCase: GetAllSchoolsUseCase,
    val searchSchoolsUseCase: SearchSchoolsUseCase,
    val getSelectedSchoolUseCase: GetSelectedSchoolUseCase,
    val getSelectedSchoolScoreUseCase: GetSelectedSchoolScoreUseCase

)