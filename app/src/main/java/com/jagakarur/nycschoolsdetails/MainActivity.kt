package com.jagakarur.nycschoolsdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jagakarur.nycschoolsdetails.navigation.SetupNavGraph
import com.jagakarur.nycschoolsdetails.ui.theme.NYCSchoolsDetailsTheme
import dagger.hilt.android.AndroidEntryPoint

//Telling where to inject those dependencies which provide
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