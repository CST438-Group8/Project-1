package com.example.project1_triviagame.ui

import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width

@Composable
fun CurvedTitleCompose(
    text: String,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val startX = w * 0.12f
        val endX = w * 0.88f
        val baseY = h * 0.78f
        val curveHeight = h * 0.25f

        val path = Path().apply {
            moveTo(startX, baseY)
            quadTo(w / 2f, baseY - curveHeight, endX, baseY)
        }

        val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color(0xFF8C817A).toArgb()
            textAlign = Paint.Align.CENTER
            typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
            textSize = 70f
            setShadowLayer(20f, 0f, 0f, Color(0xFF8C817A).toArgb())
        }

        val corePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.White.toArgb()
            textAlign = Paint.Align.CENTER
            typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
            textSize = 70f
        }

        drawIntoCanvas { canvas ->
            val native = canvas.nativeCanvas
            native.drawTextOnPath(text, path, 0f, 0f, glowPaint)
            native.drawTextOnPath(text, path, 0f, 0f, corePaint)
        }
    }
}