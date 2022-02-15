package com.jagakarur.nycschoolsdetails.presentation.screens.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jagakarur.nycschoolsdetails.R
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import com.jagakarur.nycschoolsdetails.presentation.components.InfoBox
import com.jagakarur.nycschoolsdetails.ui.theme.*
import com.jagakarur.nycschoolsdetails.util.Constants.ABOUT_TEXT_MAX_LINES


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedSchoolScore: List<SchoolScore>?
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.LightGray,
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedSchoolScore?.let {
                BottomSheetContent(
                    selectedSchool = it,
                    infoBoxIconColor = StarColor,
                    sheetBackgroundColor = Color.Black.copy(alpha = ContentAlpha.medium),
                    contentColor = MaterialTheme.colors.topAppBarContentColor
                )
            }
        },
        content = {
            selectedSchoolScore?.let {
                BackgroundContent(
                    selectedSchool = it,
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )

            }
        }
    )
}


/**
 * Bottom sheet content
 */
@ExperimentalCoilApi
@Composable
fun BottomSheetContent(
    selectedSchool: List<SchoolScore>?,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.topAppBarContentColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING)
        ) {

            Text(
                modifier = Modifier
                    .weight(8f),
                text = "SAT Score Details",
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }


        selectedSchool?.get(0)?.satReadAvgScore?.let {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_read_icon),
                iconColor = infoBoxIconColor,
                bigText = "Read Avg Score:",
                smallText = it,
                textColor = contentColor
            )


            selectedSchool[0].satWriteAvgScore?.let {
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_write_icon),
                    iconColor = infoBoxIconColor,
                    bigText = "Write Avg Score:",
                    smallText = it,
                    textColor = contentColor
                )
            }

            selectedSchool[0].satMathAvgScore?.let {
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_math_icon),
                    iconColor = infoBoxIconColor,
                    bigText = "Math Avg Score:",
                    smallText = it,
                    textColor = contentColor
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = MEDIUM_PADDING)
        ) {
            Text(
                modifier = Modifier
                    .padding(end = SMALL_PADDING),
                text = "Total Test Takers: ",
                color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold
            )
            selectedSchool?.get(0)?.satTestTakers?.let {
                Text(
                    text = it,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    maxLines = ABOUT_TEXT_MAX_LINES
                )
            }
        }

    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    selectedSchool: List<SchoolScore>?,
    onCloseClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Image",
                    tint = MaterialTheme.colors.error
                )
            }
        }

        selectedSchool?.get(0)?.schoolName?.let {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(all = LARGE_PADDING),
                text = it,
                color = MaterialTheme.colors.activeHeaderColor,
                maxLines = ABOUT_TEXT_MAX_LINES,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }










