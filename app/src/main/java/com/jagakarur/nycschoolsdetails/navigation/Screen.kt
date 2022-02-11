package com.jagakarur.nycschoolsdetails.navigation

/**
 * This data holder class for app screens with unique route
 * this route will be used while navigating each other
 * **/
sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    //required argument: dbn will get the selected school details from API
    object Details: Screen("details_screen/{dbn}"){
        fun passSchoolDBN(dbn: String):String{
            return "details_screen/$dbn"
        }
    }
    object Search: Screen("search_screen")
}
