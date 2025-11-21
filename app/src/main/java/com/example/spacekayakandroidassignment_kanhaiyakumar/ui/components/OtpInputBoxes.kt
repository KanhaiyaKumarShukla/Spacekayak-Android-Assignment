package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.model.Country
import com.example.spacekayakandroidassignment_kanhaiyakumar.model.countries

@Composable
fun OtpInputBoxes(
    otp: String,
    onOtpChange: (String) -> Unit,
) {
    val otpState = remember { mutableStateOf(otp) }

    BasicTextField(
        value = otpState.value,
        onValueChange = {
            if (it.length <= 6 && it.all { ch -> ch.isDigit() }) {
                otpState.value = it
                onOtpChange(it)
            }
        },
        modifier = Modifier.semantics {
            contentType = ContentType.SmsOtpCode
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(6) { index ->
                    OtpDigitBox(
                        char = otpState.value.getOrElse(index) { ' ' },
                        isSelected = index == otpState.value.length
                    )
                }
            }
        }
    )
}

@Composable
fun OtpDigitBox(
    char: Char,
    isSelected: Boolean
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF4AA8FF) else Color(0x33FFFFFF)
    )

    val borderWidth by animateDpAsState(
        targetValue = if (isSelected) 2.dp else 1.dp
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (char == ' ') "" else char.toString(),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}
