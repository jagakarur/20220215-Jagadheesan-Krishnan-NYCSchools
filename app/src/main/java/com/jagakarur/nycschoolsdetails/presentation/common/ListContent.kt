package com.jagakarur.nycschoolsdetails.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.navigation.Screen
import com.jagakarur.nycschoolsdetails.presentation.components.RatingWidget
import com.jagakarur.nycschoolsdetails.presentation.components.ShimmerEffect
import com.jagakarur.nycschoolsdetails.ui.theme.*

@ExperimentalCoilApi
@Composable
fun ListContent(
    schools: LazyPagingItems<School>,
    navController: NavHostController
) {
     val result = handlePagingResult(schools = schools)
    Log.d("ListContent", schools.loadState.toString())
    if (result) {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(
            items = schools,
            key = { school ->
                school.dbn
            }
        ) { school ->
            school?.let {
                SchoolItem(school = it, navController = navController)
            }
        }
    }
}
}

@Composable
fun handlePagingResult(
    schools: LazyPagingItems<School>
): Boolean {
    schools.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, schools = schools)
                false
            }
            schools.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun SchoolItem(
    school: School,
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .height(SCHOOL_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passSchoolDBN(dbn = school.dbn))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                topStart = LARGE_PADDING,
                topEnd = LARGE_PADDING,
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                school.schoolName?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                school.schoolEmail?.let {
                    Text(
                        text = it,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    school.graduationRate?.let {
                        Column {
                            Row(
                                modifier = Modifier.padding(top = SMALL_PADDING),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Graduation Rate: ",
                                    textAlign = TextAlign.Left,
                                    fontSize = MaterialTheme.typography.caption.fontSize,
                                    color = Color.White.copy(alpha = ContentAlpha.medium)
                                )
                            }

                            Row(
                                modifier = Modifier.padding(top = SMALL_PADDING),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RatingWidget(
                                    modifier = Modifier.padding(end = SMALL_PADDING),
                                    rating = it.toDouble() * 10.0
                                )
                            }
                        }
                    }


//                    Text(
//                        text = "(${it.toDouble() * 10.0})",
//                        textAlign = TextAlign.Center,
//                        color = Color.White.copy(alpha = ContentAlpha.medium)
//                    )

                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun SchoolItemPreview() {
    SchoolItem(
        school = School(
            dbn = "String",
            schoolName = "String",
            schoolEmail = "String",
            graduationRate = "5.2"
        ),
        navController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SchoolItemDarkPreview() {
    SchoolItem(
        school = School(
            dbn = "String",
            schoolName = "String",
            schoolEmail = "String",
            graduationRate = "5.2"
        ),
        navController = rememberNavController()
    )
}


