package com.example.dotaweather.ui.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlinx.android.synthetic.main.sun.view.*


class SunView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    private var progress = 0f
    private var progressPath = 0f
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val RADIUS = Utils.dpToPixel(15f)
    private var paintC = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paintD = Paint(Paint.ANTI_ALIAS_FLAG)
    private var currentPosition = FloatArray(2)
    private var path = Path()
    private var pathMeasure = PathMeasure()

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    fun getProgressPath(): Float {
        return progressPath
    }

    fun setProgressPath(progressPath: Float) {
        this.progressPath = progressPath
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
        path = Path()
        path.arcTo(
            Utils.dpToPixel(20f),
            RADIUS + Utils.dpToPixel(10f),
            width.toFloat() - Utils.dpToPixel(20f),
            Utils.dpToPixel(300f),
            180f,
            180f,
            true)
        canvas?.drawPath(path, paint)
        pathMeasure = PathMeasure(path, false)
        pathMeasure.getPosTan(progress, currentPosition, null)
        paintC.color = Color.YELLOW
        canvas?.drawCircle(
            currentPosition[0],
            currentPosition[1],
            RADIUS,
            paintC
        )
        val path2 = Path()
        path2.arcTo(
            Utils.dpToPixel(20f),
            RADIUS + Utils.dpToPixel(10f),
            width.toFloat() - Utils.dpToPixel(20f),
            Utils.dpToPixel(300f),
            180f,
            progressPath,
            true)
        paintD.apply {
            style = Paint.Style.STROKE
            strokeWidth = Utils.dpToPixel(5f)
            strokeCap = Paint.Cap.ROUND
            color = Color.YELLOW
        }
        val pathEffectD: PathEffect = DashPathEffect(floatArrayOf(Utils.dpToPixel(20f), Utils.dpToPixel(10f)), 0f)
        paintD.pathEffect = pathEffectD
        canvas?.drawPath(path2, paintD)

    }
    fun anim(percent: Float) {
        val animator = ObjectAnimator.ofFloat(sun, "progress", 0f, pathMeasure.length * percent)
        val animatorPath = ObjectAnimator.ofFloat(sun, "progressPath", 0f, 180f * percent)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator, animatorPath)
        animatorSet.duration = 2000
        animatorSet.interpolator = FastOutSlowInInterpolator()
        animatorSet.start()
    }
}