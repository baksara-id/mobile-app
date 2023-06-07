package com.baksara.app.CustomView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.abs

class AksaraDrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var drawPath: Path
    private lateinit var drawPaint: Paint
    private lateinit var canvasBitmap: Bitmap
    private lateinit var canvasPaint: Paint
    private var brushSize: Float = 0.toFloat()
    private var lastTouchX: Float = 0.toFloat()
    private var lastTouchY: Float = 0.toFloat()
    private val touchTolerance: Float


    init {
        setupDrawing()
        touchTolerance = ViewConfiguration.get(context).scaledTouchSlop.toFloat()
    }

    private fun setupDrawing() {
        drawPath = Path()
        drawPaint = Paint()
        drawPaint.color = Color.BLACK
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 5.toFloat()
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap, 0.toFloat(), 0.toFloat(), canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.moveTo(touchX, touchY)
                lastTouchX = touchX
                lastTouchY = touchY
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = abs(touchX - lastTouchX)
                val dy = abs(touchY - lastTouchY)
                if (dx >= touchTolerance || dy >= touchTolerance) {
                    drawPath.quadTo(lastTouchX, lastTouchY, (touchX + lastTouchX) / 2, (touchY + lastTouchY) / 2)
                    lastTouchX = touchX
                    lastTouchY = touchY
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                drawPath.lineTo(lastTouchX, lastTouchY)
                canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(canvasBitmap)
                draw(canvas)
            }
            else -> return false
        }

        invalidate()
        return true
    }

    fun convertToJpg(): ByteArray {
        val stream = ByteArrayOutputStream()
        canvasBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    
    fun clear() {
        drawPath.reset()
        canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(canvasBitmap)
        draw(canvas)
        invalidate()
    }
}