package com.jagakarur.nycschoolsdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jagakarur.nycschoolsdetails.navigation.SetupNavGraph
import com.jagakarur.nycschoolsdetails.ui.theme.NYCSchoolsDetailsTheme
import dagger.hilt.android.AndroidEntryPoint

//Telling where to inject those dependencies which provide
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCSchoolsDetailsTheme {
                //Going to remember a new controller composable function to initialize our nav controller
                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}
