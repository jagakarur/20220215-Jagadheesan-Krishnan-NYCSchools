package com.jagakarur.nycschoolsdetails.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import com.jagakarur.nycschoolsdetails.Constants.DETAILS_ARGUMENT_KEY
import com.jagakarur.nycschoolsdetails.presentation.screens.splash.SplashScreen
import com.jagakarur.nycschoolsdetails.presentation.screens.welcome.WelcomeScreen


/**
 * NavHostController will pass from MainActivity
 *
 */

@Composable
fun SetupNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ){
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)

        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)

        }
        composable(route = Screen.Home.route) {

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