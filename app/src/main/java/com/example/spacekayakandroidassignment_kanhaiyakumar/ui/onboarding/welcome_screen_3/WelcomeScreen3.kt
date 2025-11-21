package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.welcome_screen_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.BottomPrimaryButton
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.PageIndicator
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WelcomeScreen3(
    onGetStartedClick: () -> Unit,
    currentPage: Int = 2,
    totalPages: Int = 3
) {
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
            painter = painterResource(id = R.drawable.welcome_screen3_img),
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
                text = "Recover lost money if tricked, with shield Protect up to 1,00,000",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 24.dp)
            )

            // Page indicator dots
            PageIndicator(
                pageCount = totalPages,
                currentPage = currentPage,
                modifier = Modifier.padding(vertical = 16.dp)
            )


            Spacer(modifier = Modifier.height(32.dp))

            // Next button
            BottomPrimaryButton(
                text = "Get Started",
                backgroundColor = Color.White,
                textColor = R.color.dark_text,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)
            ) {
                onGetStartedClick()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Welcome Screen 3 Preview"
)
@Composable
fun WelcomeScreen1Preview() {
    // Optional: wrap in a theme if you have one
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        WelcomeScreen3(
            onGetStartedClick = {}
        )
    }
}

