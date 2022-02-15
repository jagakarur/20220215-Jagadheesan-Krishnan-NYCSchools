package com.jagakarur.nycschoolsdetails.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import com.jagakarur.nycschoolsdetails.presentation.common.EmptyScoreScreen
import com.jagakarur.nycschoolsdetails.presentation.common.EmptyScreen

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedSchoolScore by detailsViewModel.selectedSchoolScore.collectAsState()

    val result = selectedSchoolScore?.let { handlePagingResult(selectedSchoolScore = it, navHostController) }

    if(result == true) {
        DetailsContent(
            navController = navHostController,
            selectedSchoolScore = selectedSchoolScore
        )
    }
}
@Composable
fun handlePagingResult(
    selectedSchoolScore:  List<SchoolScore>,
    navHostController: NavHostController
): Boolean {
    selectedSchoolScore.apply {
        return when {
            selectedSchoolScore.isEmpty() -> {
                EmptyScoreScreen(navHostController)
                false
            }
            else -> true
        }
    }
}
