package org.lightsys.kriolbiblewordfind

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat.getActionMasked
import android.widget.RelativeLayout
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout.LayoutParams
import kotlinx.android.synthetic.main.activity_sample.*

class sampleActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        var viewReleased = true

        sampleLayout.setOnTouchListener { v: View, m: MotionEvent ->
            if(viewReleased){
                val x = m.rawX
                val y = m.rawY
                button.text = "X: $x, Y: $y"

                val view = TextView(this)
                view.layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                )
                view.setBackgroundColor(Color.BLUE)
                view.id = counter
                view.alpha = 0.5F
                view.text = "AHHHHHHHHHHHHHH"
                view.textSize = 20F
                sampleLayout.addView(view)

                var coord = intArrayOf(0, 0)
                view.getLocationOnScreen(coord)
                text.text = "AbsX: " + coord[0] + " AbsY: " + coord[1]

                trackToTouch(view, m, coord[0], coord[1])
                //view.x = x - location[0]
                //view.y = y - location[1]
                counter++
            }

            when(m.actionMasked){
                MotionEvent.ACTION_DOWN -> viewReleased = false //mark letter touched (add to an array)
                MotionEvent.ACTION_UP ->  viewReleased = true //if x or y diff, create a letter from result
                MotionEvent.ACTION_MOVE -> text.text = "MOVING"

            }
            
            true
        }


        button.setOnClickListener {
            val view = TextView(this)
            view.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            view.setBackgroundColor(Color.BLUE)
            view.id = counter
            view.alpha = 0.5F
            view.text = "AHHHHHHHHHHHHHH"
            view.textSize = 20F
            sampleLayout.addView(view)

            //Get absolute position of view
            var coord = intArrayOf(0, 0)
            view.getLocationOnScreen(coord)
            button.text = "AbsX: " + coord[0] + " AbsY: " + coord[1]

            view.setOnTouchListener { v: View, m: MotionEvent ->
                //trackToTouch(v, m, coord[0], coord[1])
                true
            }
            counter++
        }
    }

    private fun trackToTouch( v: View, m: MotionEvent, absX: Int, absY: Int)
    {
        val x = m.rawX
        val y = m.rawY

        var actionString: String

        when (m.actionMasked)
        {
            MotionEvent.ACTION_DOWN -> text.text = "PRESS" //mark letter touched (add to an array)
            MotionEvent.ACTION_UP -> text.text = "RELEASE" //if x or y diff, create a letter from result
            MotionEvent.ACTION_MOVE -> text.text = "MOVE"  //track movement, if hit bound of another letter, add to array
            //need a way to limit direction once started
            //wait till next letter touched, determine from that by amount of change in x and y
            //(if only y past tolerance, vert;
            //if only x past tolerance, horiz;
            //if both past tolerance, diag)
            //(Also set direction by whether change in x/y are positive or negative)
            //Dirs will be: left, right, down, up, diag_LD, diag_LU, diag_RD, diag_RU
            else -> actionString = ""
        }

        button.text = "X: $x, Y: $y"

        v.x = x - absX
        v.y = y - absY
    }
}