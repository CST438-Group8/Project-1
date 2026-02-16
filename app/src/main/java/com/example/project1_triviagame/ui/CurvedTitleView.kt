package com.example.project1_triviagame.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CurvedTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val text = "HANGCEPTION"

    private val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#8C817A") // taupe glow
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
        textSize = 70f
        // glow
        setShadowLayer(20f, 0f, 0f, Color.parseColor("#8C817A"))
    }

    private val corePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFFFFF") // white core
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
        textSize = 70f
    }

    private val path = Path()

    init {
        // Needed for shadow/glow to appear on some devices
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Slight crescent curve
        val w = width.toFloat()
        val h = height.toFloat()

        path.reset()
        val startX = w * 0.12f
        val endX = w * 0.88f
        val baseY = h * 0.78f
        val curveHeight = h * 0.25f  // adjust for more/less curve

        path.moveTo(startX, baseY)
        path.quadTo(w / 2f, baseY - curveHeight, endX, baseY)

        // draw glow first, then core on top
        canvas.drawTextOnPath(text, path, 0f, 0f, glowPaint)
        canvas.drawTextOnPath(text, path, 0f, 0f, corePaint)
    }
}