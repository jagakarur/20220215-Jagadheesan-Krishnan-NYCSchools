package com.jagakarur.nycschoolsdetails.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.jagakarur.nycschoolsdetails.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllSchools = useCases.getAllSchoolsUseCase()
}