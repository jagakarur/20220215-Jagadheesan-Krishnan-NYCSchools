package com.jagakarur.nycschoolsdetails.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.jagakarur.nycschoolsdetails.presentation.common.ListContent

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allSchools = homeViewModel.getAllSchools.collectAsLazyPagingItems()
    // println(allSchools)
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
            ListContent(
                schools = allSchools,
                navController = navController
            )
        }
    )

}