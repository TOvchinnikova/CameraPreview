package com.t_ovchinnikova.android.camerapreview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ViewFinderOverlay(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val boxPaint: Paint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 1.toFloat()
    }

    @SuppressLint("ResourceType")
    private val scrimPaint: Paint = Paint().apply {
        color = Color.BLACK
    }

    private val eraserPaint: Paint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private val boxCornerRadius: Float =
        8.toFloat()

    private var boxRect: RectF? = null

    init {
        setViewFinder()
    }

    fun setViewFinder() {
        val overlayWidth = width.toFloat()
        val overlayHeight = height.toFloat()

        val rectTop = overlayHeight * DESIRED_HEIGHT_CROP_PERCENT / 2 / 100f
        val rectLeft = overlayWidth * DESIRED_WIDTH_CROP_PERCENT / 2 / 100f
        val rectRight = overlayWidth * (1 - DESIRED_WIDTH_CROP_PERCENT / 2 / 100f)
        val rectBottom = overlayHeight * (1 - DESIRED_HEIGHT_CROP_PERCENT / 2 / 100f)
        boxRect = RectF(rectLeft, rectTop, rectRight, rectBottom)

        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        boxRect?.let {

            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), scrimPaint)

            eraserPaint.style = Paint.Style.FILL
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, eraserPaint)
            eraserPaint.style = Paint.Style.STROKE
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, eraserPaint)
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, boxPaint)
        }
    }

    companion object {
        const val DESIRED_WIDTH_CROP_PERCENT = 20
        const val DESIRED_HEIGHT_CROP_PERCENT = 74
    }
}