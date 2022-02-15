package com.jagakarur.nycschoolsdetails.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.jagakarur.nycschoolsdetails.R
import com.jagakarur.nycschoolsdetails.ui.theme.*

@Composable
fun EmptyScoreScreen(
    navHostController: NavHostController
) {
    val message by remember {
        mutableStateOf("SAT Score not found for this school!")
    }
    val icon by remember {
        mutableStateOf(R.drawable.ic_no_data)
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyScoreContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        navHostController = navHostController
    )

}

@Composable
fun EmptyScoreContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { navHostController.popBackStack() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Image",
                    tint = MaterialTheme.colors.error
                )
            }
        }
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }


}



