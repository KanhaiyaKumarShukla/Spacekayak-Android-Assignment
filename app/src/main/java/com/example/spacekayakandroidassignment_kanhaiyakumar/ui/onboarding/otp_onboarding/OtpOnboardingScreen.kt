package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.otp_onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.BottomPrimaryButton
import com.example.spacekayakandroidassignment_kanhaiyakumar.viewmodel.BottomSheetViewModel

@Composable
fun OTPOnboardingScreen(
    nextPage: () -> Unit,
){

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF62D6FF),
            Color.White
        ),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
            .padding(horizontal = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(130.dp))

            // Center Icon
            Image(
                painter = painterResource(id = R.drawable.otp_security_img),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Private by Design.\nYou're in Control.",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Color(0xFF0A0A0A),
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Shield protects you from scams, risky links, and\nharmful apps, all while keeping your data private\nand secure.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            InfoRow(
                icon = R.drawable.otp_onboarding_lock_icon,
                text = "All checks happen on your phone;\ncontacts and chats stay private."
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(
                icon = R.drawable.otp_onboarding_security_icon,
                text = "We request only permissions\nneeded to keep you safe."
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(
                icon = R.drawable.otp_onboarding_clock_icon,
                text = "Control your data access anytime.\nWe never sell your information."
            )
        }

        var size by remember { mutableStateOf(IntSize.Zero) }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
                .fillMaxWidth()
                .height(48.dp)
                .onGloballyPositioned { size = it.size }
        ) {

            if (size.width > 0 && size.height > 0) {

                val radialBackground = Brush.radialGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient_blue),
                        colorResource(id = R.color.gradient_dark_blue),
                    ),
                    center = Offset(
                        size.width / 2f,          // center X
                        size.height.toFloat()     // bottom-center Y
                    ),
                    radius = size.height * 1.6f   // spreading
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = radialBackground,
                            shape = RoundedCornerShape(24.dp)
                        )
                )
            }

            BottomPrimaryButton(
                text = "I understand",
                backgroundColor = Color.Transparent,
                textColor = R.color.white,
                modifier = Modifier.fillMaxSize()
            ) {
                nextPage()
            }
        }



    }
}

@Composable
fun InfoRow(icon: Int, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF1A9FFF),
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color(0xFF3A3A3A)
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun OTPOnboardingScreenPreview() {
    OTPOnboardingScreen(
        nextPage = {},
    )
}