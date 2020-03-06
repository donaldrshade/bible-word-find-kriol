package org.lightsys.kriolbiblewordfind

import android.app.Activity
import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import android.util.DisplayMetrics
import android.widget.TextView

class PopUp(val hasText: Boolean = false, val setText: String = "",val hasTick:Boolean=false): Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pop_up)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = (0.4 * dm.widthPixels).toInt()
        val height = (0.25 * dm.heightPixels).toInt()
        window.setLayout(width,height)
        val popUpText = findViewById<TextView>(R.id.pop_up_text)
        if(hasText){
            popUpText.text = setText
        }
        else{
            popUpText.visibility = TextView.INVISIBLE
        }
        if(hasTick){

        }

    }
}