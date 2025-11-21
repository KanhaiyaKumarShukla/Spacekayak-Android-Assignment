package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.BottomPrimaryButton
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.PageIndicator

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomePagerScreen(
    //onShowBottomSheet: () -> Unit,
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val pagesCount = 3

    // set system bars color to gradient_navy
    val systemUiController = rememberSystemUiController()
    val barColor = colorResource(id = R.color.gradient_navy)
    LaunchedEffect(Unit) {
        systemUiController.setNavigationBarColor(color = barColor, darkIcons = false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pagesCount,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> WelcomePage(
                    imageRes = R.drawable.welcome_screen1_img,
                    title = "Get instant alerts for scam calls, suspicious texts, harmful apps, and breaches",
                    buttonText = "Next",
                    pagerState = pagerState,
                    onButtonClick = {
                        coroutineScope.launch {
                            val next = pagerState.currentPage + 1
                            if (next < pagesCount) {
                                pagerState.animateScrollToPage(next)
                            } else {
                                // Finish onboarding -> show bottom sheet (OTP)
                                //onShowBottomSheet()
                                onFinish()
                            }
                        }
                    }
                )
                1 -> WelcomePage(
                    imageRes = R.drawable.welcome_screen_2_img,
                    title = "Protect your family from scams with real-time alerts and safety monitoring",
                    buttonText = "Next",
                    pagerState = pagerState,
                    onButtonClick = {
                        coroutineScope.launch {
                            val next = pagerState.currentPage + 1
                            if (next < pagesCount) {
                                pagerState.animateScrollToPage(next)
                            } else {
                                // Finish onboarding -> show bottom sheet (OTP)
                                //onShowBottomSheet()
                                onFinish()
                            }
                        }
                    }
                )
                2 -> WelcomePage(
                    imageRes = R.drawable.welcome_screen3_img,
                    title = "Recover lost money if tricked, with shield Protect up to 1,00,000",
                    buttonText = "Get Started",
                    pagerState = pagerState,
                    onButtonClick = {
                        coroutineScope.launch {
                            val next = pagerState.currentPage + 1
                            if (next < pagesCount) {
                                pagerState.animateScrollToPage(next)
                            } else {
                                // Finish onboarding -> show bottom sheet (OTP)
                                //onShowBottomSheet()
                                onFinish()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun WelcomePage(
    imageRes: Int,
    title: String,
    buttonText: String,
    pagerState: PagerState,
    onButtonClick: () -> Unit
) {
    /*
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // gradient overlay bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colorResource(id = R.color.gradient_blue),
                            colorResource(id = R.color.gradient_dark_blue),
                            colorResource(id = R.color.gradient_navy)
                        )
                    )
                )
        )

        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-80).dp)
        )
    }

     */
    val gradientColors = listOf(
        Color.Transparent,
        colorResource(id = R.color.gradient_blue),
        colorResource(id = R.color.gradient_dark_blue),
        colorResource(id = R.color.gradient_navy)
    )
    val systemUiController = rememberSystemUiController()
    val navColor = colorResource(id = R.color.gradient_navy)

    LaunchedEffect(Unit) {
        systemUiController.setNavigationBarColor(
            color = navColor,
            darkIcons = false  // use white icons on dark background
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Main content
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        // Gradient overlay at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = gradientColors,
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Bottom content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 24.dp)
            )

            // Page indicator dots
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.4f),
                indicatorWidth = 8.dp,
                spacing = 8.dp
            )


            Spacer(modifier = Modifier.height(32.dp))

            // Next button
            BottomPrimaryButton(
                text = buttonText,
                backgroundColor = Color.White,
                textColor = R.color.dark_text,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)
            ) {
                onButtonClick()
            }
        }
    }
}
