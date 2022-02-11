package com.jagakarur.nycschoolsdetails.domain.model

import androidx.annotation.DrawableRes
import com.jagakarur.nycschoolsdetails.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First: OnBoardingPage(
        image = R.drawable.ic_welcome_img,
        title = "Welcome to NYC Schools Finder",
        description = "This application will hep us to find the SAT score details for schools in NYC."
    )
    object Second : OnBoardingPage(
        image = R.drawable.ic_search_foreground,
        title = "Search and Select School to See SAT Score Details",
        description = "This page help us to select the school from NYC schools list."
    )

    object Third : OnBoardingPage(
        image = R.drawable.ic_details_foreground,
        title = "SAT Score Details Page",
        description = "This page will provide the STA score details of selected school."
    )

}

