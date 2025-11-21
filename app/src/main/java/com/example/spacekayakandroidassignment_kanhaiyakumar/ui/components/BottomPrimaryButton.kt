package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
@Composable
fun BottomPrimaryButton(
    text: String,
    backgroundColor: Color,
    @ColorRes textColor: Int = R.color.dark_text,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(312.dp)                   // Figma width
            .height(48.dp)                   // Figma height
            .clip(RoundedCornerShape(56.dp)) // Figma radius
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 22.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colorResource(id = textColor),   // Correct way to use Int color
            fontSize = 16.sp,                        // Figma 16px
            fontWeight = FontWeight.SemiBold,        // Figma weight 600
            lineHeight = 22.4.sp,                    // 16 * 1.4 = 22.4 (140%)
            letterSpacing = (-0.32).sp,              // -2% of 16px → -0.32sp
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Call Action Buttons Preview"
)
@Composable
fun CallActionButtonsPreview() {
    // Wrap in a surface or background if needed
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        BottomPrimaryButton(
            text = "button",
            backgroundColor = Color(0xFF2DCE4C),
            modifier = Modifier.padding(16.dp),
            onClick = {}
        )
    }
}
