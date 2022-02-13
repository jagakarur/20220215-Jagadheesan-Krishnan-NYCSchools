package com.jagakarur.nycschoolsdetails.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jagakarur.nycschoolsdetails.util.Constants.DETAILS_ARGUMENT_KEY
import com.jagakarur.nycschoolsdetails.presentation.screens.home.HomeScreen
import com.jagakarur.nycschoolsdetails.presentation.screens.splash.SplashScreen
import com.jagakarur.nycschoolsdetails.presentation.screens.welcome.WelcomeScreen


/**
 * NavHostController will pass from MainActivity
 *
 */
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {

        }
        composable(route = Screen.Search.route) {

        }
    }

}