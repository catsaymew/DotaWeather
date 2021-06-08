package com.example.dotaweather.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class SunView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    private var progress = 0f
    private var paint = Paint()

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = Utils.dpToPixel(5f)
            strokeCap = Paint.Cap.ROUND
            color = Color.parseColor("#ffffff")
        }
        val pathEffect: PathEffect = DashPathEffect(floatArrayOf(Utils.dpToPixel(20f), Utils.dpToPixel(10f)), 0f)
        paint.pathEffect = pathEffect
        var path = Path()
        path.arcTo(
            Utils.dpToPixel(10f),
            Utils.dpToPixel(10f),
            width.toFloat() - 10f,
            Utils.dpToPixel(300f),
            180f,
            180f,
            true)
        canvas?.drawPath(path, paint)

//        paint.color = Color.parseColor("#E91E63")
//        val path2 = Path()
//        path2.arcTo(
//            centerX - radius,
//            centerY - radius,
//            centerX + radius,
//            centerY + radius,
//            135f,
//            progress,
//            true)
//        canvas?.drawPath(path2, paint)

    }
}