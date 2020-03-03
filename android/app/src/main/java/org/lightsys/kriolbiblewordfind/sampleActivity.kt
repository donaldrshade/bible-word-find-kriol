package org.lightsys.kriolbiblewordfind

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat.getActionMasked
import android.widget.RelativeLayout
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_sample.*

class sampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        var text = findViewById<TextView>(R.id.text)
        var button = findViewById<Button>(R.id.button)
        var image = findViewById<ImageView>(R.id.image)

        image.setOnTouchListener {v: View, img: MotionEvent ->
            text.setText("Good evening Master Wayne")
            handleTouch(img)
            true
        }

        text.setOnTouchListener {v: View, txt: MotionEvent ->
            text.setText("I'M BATMAN")
            handleTouch(txt)
            true
        }
    }

    private fun handleTouch(m: MotionEvent)
    {
        val pointerCount = m.pointerCount
        val x = m.getX(0)
        val y = m.getY(0)

            var actionString: String

            when (m.actionMasked)
            {
                MotionEvent.ACTION_DOWN -> actionString = "DOWN" //mark letter touched (add to an array)
                MotionEvent.ACTION_UP -> actionString = "UP" //if x or y diff, create a letter from result
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> actionString = "PNTR UP"
                MotionEvent.ACTION_MOVE -> actionString = "MOVE"    //track movement, if hit bound of another letter, add to array
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
    }
}