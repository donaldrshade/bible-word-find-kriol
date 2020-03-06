package org.lightsys.kriolbiblewordfind

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sample.*

class sampleActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        //Create canvas and add it to layout
        var canvas = DrawingView(this, null, findViewById<TextView>(R.id.text))
        var params = LayoutParams(
            puzzle.layoutParams.width,
            puzzle.layoutParams.height)
        puzzle.addView(canvas, params)
        canvas.bringToFront()

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            finish()
        }

        /*for(i in 0..6){
            for(j in 0..6){
                canvas.setOnTouchListener { v: View, m: MotionEvent ->
                    var touchX = m.x.toInt()
                    var touchY =m.y.toInt()

                    if(rect.contains(m.x.toInt(), m.y.toInt())){
                        text.text = "Image Touched!"
                    } else {
                        text.text = "$touchX $touchY"
                    }

                    true
                }
            }
        }*/
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        image.setOnTouchListener { v: View, m: MotionEvent ->
            var startX = 0F
            var startY = 0F
            var endX = 0F
            var endY = 0F

            when (m.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    startX = m.rawX
                    startY = m.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    endX = m.rawX
                    endY = m.rawY
                }
                MotionEvent.ACTION_UP -> {
                    text.text = "$startX $startY $endX $endY"

                    c.drawLine(200F, 200F, 356F, 653F, Paint())
                }
            }


            //c.drawLine(m.x, m.y, )
            true
        }
    }

    private fun trackToTouch( v: View, m: MotionEvent)
    {
        val x = m.rawX
        val y = m.rawY


        when (m.actionMasked)
        {
            //MotionEvent.ACTION_DOWN -> text.text = "PRESS" //mark letter touched (add to an array)
            //MotionEvent.ACTION_UP -> text.text = "RELEASE" //if x or y diff, create a letter from result
            //MotionEvent.ACTION_MOVE -> text.text = "MOVE"
            //(Also set direction by whether change in x/y are positive or negative)
            //Dirs will be: left, right, down, up, diag_LD, diag_LU, diag_RD, diag_RU
            MotionEvent.ACTION_MOVE -> {
                button.text = "X: $x, Y: $y"

                var coord = intArrayOf(0, 0)
                val id = m.getPointerId(0)
                v.getLocationOnScreen(coord)
                text.text = "CoordX: " + coord[0] + " CoordY: " + coord[1]

                v.x = x - coord[0]
                v.y = y - coord[1]
            }
        }
    }*/
}