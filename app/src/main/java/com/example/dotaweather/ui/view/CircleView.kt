package com.example.dotaweather.ui.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class CircleView : View {
//    val a = width
    private var progress = 0f
    private var paint = Paint()
    private val radius = Utils.dpToPixel(60f)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = Utils.dpToPixel(10f)
            strokeCap = Paint.Cap.ROUND
            color = Color.parseColor("#ffffff")
        }
        val path = Path()
        path.arcTo(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius,
            135f,
            270f,
            true)
        canvas?.drawPath(path, paint)

        paint.color = Color.parseColor("#E91E63")

        val pathAnim = Path()
        pathAnim.arcTo(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius,
            135f,
            progress,
            true)
        canvas?.drawPath(pathAnim, paint)

    }

}