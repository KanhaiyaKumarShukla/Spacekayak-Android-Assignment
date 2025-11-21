package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spacekayakandroidassignment_kanhaiyakumar.R
import androidx.compose.ui.unit.IntSize

@Composable
fun RadialGradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = 24.dp,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { size = it.size }
            .height(48.dp),
        contentAlignment = Alignment.Center
    ) {

        if (size.width > 0) {
            val radialBackground = Brush.radialGradient(
                colors = listOf(
                    colorResource(id = R.color.gradient_blue),
                    colorResource(id = R.color.gradient_dark_blue),
                ),
                center = Offset(
                    size.width / 2f,
                    size.height.toFloat()
                ),
                radius = size.height * 1.6f
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = radialBackground,
                        shape = RoundedCornerShape(cornerRadius)
                    )
            )
        }

        // Actual Button Content
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Call Action Buttons Preview"
)
@Composable
fun RadialGradientButtonPreview() {
    // Wrap in a surface or background if needed
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        RadialGradientButton(
            text = "button",
            modifier = Modifier.padding(16.dp),
            onClick = {}
        )
    }
}

