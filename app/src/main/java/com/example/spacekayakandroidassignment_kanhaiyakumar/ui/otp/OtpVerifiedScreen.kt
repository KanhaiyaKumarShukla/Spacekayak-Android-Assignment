package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.sheetBackground
import com.example.spacekayakandroidassignment_kanhaiyakumar.R

@Composable
fun OtpVerifiedScreen(){
    var size by remember { mutableStateOf<IntSize?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { size = it.size }
            .sheetBackground(size)      // <-- custom gradient modifier
            .padding( 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(90.dp))

            // -------- Verified Icon --------
            Image(
                painter = painterResource(id = R.drawable.ic_otp_verified),
                contentDescription = "Verified",
                modifier = Modifier.size(118.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            // -------- Title Text --------
            Text(
                text = "Your phone is\nverified securely.",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                lineHeight = 34.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // -------- Subtitle Text --------
            Text(
                text = "OTP Verified safely with an OTP. Stay alert on\nfake OTP scams.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color(0xB3FFFFFF), // 70% white
                lineHeight = 20.sp
            )
        }
    }

}
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Welcome Screen 3 Preview"
)
@Composable
fun OtpVerificationScreenPreview() {
    // Optional: wrap in a theme if you have one
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        OtpVerifiedScreen(
        )
    }
}