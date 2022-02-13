package com.jagakarur.nycschoolsdetails.presentation.screens.welcome


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.jagakarur.nycschoolsdetails.util.Constants.LAST_ON_BOARDING_PAGE
import com.jagakarur.nycschoolsdetails.util.Constants.ON_BOARDING_PAGE_COUNT
import com.jagakarur.nycschoolsdetails.R
import com.jagakarur.nycschoolsdetails.domain.model.OnBoardingPage
import com.jagakarur.nycschoolsdetails.navigation.Screen
import com.jagakarur.nycschoolsdetails.ui.theme.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable

//hiltViewModel is used from "androidx.hilt:hilt-navigation-compose" dependency

fun WelcomeScreen(navController: NavHostController, welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {

        //A horizontally scrolling layout that allows users to flip between items to the left and right.

        HorizontalPager(
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) { page: Int ->
            PagerScreen(onBoardingPage = pages[page])
        }
        //this will create horizontal pager indicator
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }

    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }

}

//UI component setup
@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}