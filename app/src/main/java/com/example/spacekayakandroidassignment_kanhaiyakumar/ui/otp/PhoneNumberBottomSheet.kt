package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp


import android.widget.Toast
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.sheetBackground
import com.example.spacekayakandroidassignment_kanhaiyakumar.model.countries
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.CountryCodeDropdown
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components.RadialGradientButton

@Composable
fun PhoneNumberBottomSheet(
    onClose: () -> Unit,
    onContinue: (String) -> Unit
) {
    var size by remember { mutableStateOf<IntSize?>(null) }
    var phone by remember { mutableStateOf("") }
    val context = LocalContext.current
    var selectedCountry by remember { mutableStateOf(countries[0]) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { size = it.size }
            .sheetBackground(size)      // <-- custom gradient modifier
            .padding( start = 24.dp, bottom = 24.dp, end = 24.dp, top=5.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            // Title
            Text(
                text = "Verify your \nPhone Number",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "We're verifying your number securely.\nAn OTP may be sent if needed.",
                color = Color(0xCCFFFFFF),
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )

            Spacer(Modifier.height(28.dp))

            // Phone input box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(Modifier.width(12.dp))

                CountryCodeDropdown(
                    selectedCountry = selectedCountry,
                    onCountrySelected = { country ->
                        selectedCountry = country
                    },
                    modifier = Modifier.width(52.dp)
                )

                // Spacer(Modifier.width(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = {
                        Text("Enter Phone Number", color = Color(0xFF888B92))
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    colors = androidx.compose.material3.TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(20.dp))

            // Continue button
            RadialGradientButton(
                text = "Continue",
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                onClick = {
                    if (phone.isBlank()) {
                        Toast.makeText(context, "Please enter your phone number", Toast.LENGTH_SHORT).show()
                        return@RadialGradientButton
                    }

                    if (phone.length < 10) {
                        Toast.makeText(context, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                        return@RadialGradientButton
                    }
                    val fullPhoneNumber = "${selectedCountry.dialCode}$phone"
                    onContinue(fullPhoneNumber)
                }
            )

            val termsText = buildAnnotatedString {

                append("By continuing, I confirm I am at least 18 years old and agree to Shield’s ")

                // Terms
                pushStringAnnotation(tag = "terms", annotation = "terms")
                withStyle(SpanStyle(color = colorResource(R.color.gradient_blue))) { append("Terms") }
                pop()

                append(" and ")

                // Privacy Policy
                pushStringAnnotation(tag = "policy", annotation = "policy")
                withStyle(SpanStyle(color = colorResource(R.color.gradient_blue))) { append("Privacy Policy") }
                pop()

                append(", and receiving SMS alerts.")
            }

            ClickableText(
                text = termsText,
                style = TextStyle(
                    color = Color(0x80FFFFFF),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 12.dp),
                onClick = { offset ->
                    termsText.getStringAnnotations(start = offset, end = offset)
                        .firstOrNull()?.let {
                            when (it.tag) {
                                "terms" -> Toast.makeText(context, "Clicked Terms", Toast.LENGTH_SHORT).show()
                                "policy" -> Toast.makeText(context, "Clicked Privacy Policy", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            )


            Spacer(Modifier.height(20.dp))
        }

        // Close button
        Icon(
            painter = painterResource(id = R.drawable.bottom_sheet_close_ic),
            contentDescription = "Close",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(32.dp)
                .clickable { onClose() }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Welcome Screen 3 Preview"
)
@Composable
fun Preview() {
    // Optional: wrap in a theme if you have one
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        PhoneNumberBottomSheet(
            onClose = {},
            onContinue = {}
        )
    }
}
