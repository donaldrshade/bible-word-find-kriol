package org.lightsys.kriolbiblewordfind

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Layout
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.pop_up.view.*

class PopUp(): Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pop_up)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((0.4 * width).toInt(),(0.25 * height).toInt())
        window.attributes.gravity = (Gravity.START or Gravity.TOP)

        val setText: String = intent.getStringExtra(getString(R.string.setText))
        val hasTick: Boolean = intent.getBooleanExtra(getString(R.string.hasTick),false)
        val xPos: Int = (intent.getIntExtra(getString(R.string.X_POS),0)+(0.3*width)).toInt()
        val yPos: Int = (intent.getIntExtra(getString(R.string.Y_POS),0)+(0.375*height)).toInt()
        window.attributes.x = xPos
        window.attributes.y = yPos
        val popUpText = findViewById<TextView>(R.id.pop_up_text)
        val tickImage = findViewById<ImageView>(R.id.tick_image)
        val popUpBackground = findViewById<RelativeLayout>(R.id.pop_up_background)
        if(setText!=""){
            popUpText.visibility = TextView.VISIBLE
            popUpText.text = setText
        }
        else{
            popUpText.visibility = TextView.INVISIBLE
        }
        if(hasTick){
            tickImage.visibility = ImageView.VISIBLE
        }else{
            tickImage.visibility = ImageView.INVISIBLE
        }

    }
}
fun PopUp(act:Activity,setText:String = "",hasTick:Boolean = false,xPos:Int = 0,yPos:Int = 0){
    val intent = Intent(act,PopUp()::class.java)
    intent.putExtra(act.getString(R.string.hasTick),hasTick)
    intent.putExtra(act.getString(R.string.setText),setText)
    intent.putExtra(act.getString(R.string.X_POS),xPos)
    intent.putExtra(act.getString(R.string.Y_POS),yPos)
    act.startActivity(intent)
}