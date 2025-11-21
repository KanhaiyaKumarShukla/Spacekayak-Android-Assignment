package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.OtpInputBoxes
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.RadialGradientButton
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.sheetBackground

@Composable
fun OtpVerificationBottomSheet(
    phoneNumber: String,
    onBack: () -> Unit,
    onOtpChange: (String) -> Unit,
    onResend: () -> Unit,
    onVerify: () -> Unit
) {
    var size by remember { mutableStateOf<IntSize?>(null) }
    var otp by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { size = it.size }
            .sheetBackground(size)
            .padding( start = 24.dp, bottom = 24.dp, end = 24.dp, top=5.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bottom_sheet_prev_ic),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onBack() }
                )
            }

            // Title
            Text(
                text = "Enter the OTP sent\nto your Phone",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // Subtitle with editable phone
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "We sent a 6 digit code to ",
                    color = Color(0xCCFFFFFF),
                    fontSize = 14.sp
                )
                Text(
                    text = "+91 $phoneNumber",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painter = painterResource(id = R.drawable.otp_edit_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(start = 6.dp)
                        .clickable { onBack() }
                )
            }

            Spacer(Modifier.height(30.dp))
            OtpInputBoxes(
                otp = otp,
                onOtpChange = {
                    otp = it
                    onOtpChange(it)
                }
            )


            Spacer(Modifier.height(20.dp))

            // Resend section
            Text(
                text = "Didn’t receive the code?",
                color = Color(0x80FFFFFF),
                fontSize = 14.sp
            )

            Text(
                text = "Resend Code",
                color = Color(0xFF4AA8FF),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .clickable { onResend() }
            )
            Spacer(Modifier.height(40.dp))

            RadialGradientButton(
                text = "Verify my number",
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                onClick = onVerify
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
fun OtpVerificationBottomSheetPreview() {
    // Optional: wrap in a theme if you have one
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        OtpVerificationBottomSheet(
            phoneNumber = "1234567890",
            onBack = {},
            onOtpChange = {},
            onResend = {},
            onVerify = {}
        )
    }
}
