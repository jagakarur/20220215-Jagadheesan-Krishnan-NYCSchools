package com.jagakarur.nycschoolsdetails.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagakarur.nycschoolsdetails.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @HiltViewModel will help to inject respective dependencies in this view model
 */


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        //Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a Job.
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingUseCase(completed = completed)
        }
    }

}