package com.jagakarur.nycschoolsdetails.domain.use_cases.save_onboarding

import com.jagakarur.nycschoolsdetails.data.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}