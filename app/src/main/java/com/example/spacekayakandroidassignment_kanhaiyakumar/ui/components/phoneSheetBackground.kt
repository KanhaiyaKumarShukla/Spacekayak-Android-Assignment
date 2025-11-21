package com.example.spacekayakandroidassignment_kanhaiyakumar.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

fun Modifier.sheetBackground(size: IntSize?): Modifier =
    this.drawBehind {

        if (size == null) return@drawBehind

        // 1. Vertical dark gradient (main background)
        val linear = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF000926),
                Color(0xFF001F52)
            ),
            startY = 0f,
            endY = size.height.toFloat()
        )

        drawRect(linear)

        // 2. Radial glow (245% width, 100% height)
        val radius = size.width * 2.45f

        val radial1 = Brush.radialGradient(
            colors = listOf(
                Color(0x1F007EEB),
                Color(0x13007EEB),
                Color(0x00007EEB)
            ),
            center = Offset(size.width / 2f, size.height.toFloat()),
            radius = radius
        )

        drawRect(radial1)

        // 3. Second subtle glow layer
        val radial2 = Brush.radialGradient(
            colors = listOf(
                Color(0x1F167CE3),
                Color(0x13167CE3),
                Color(0x00167CE3)
            ),
            center = Offset(size.width / 2f, size.height.toFloat()),
            radius = radius
        )

        drawRect(radial2)
    }
