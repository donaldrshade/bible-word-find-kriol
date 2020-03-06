package org.lightsys.kriolbiblewordfind

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.TextView

class PopUp(): Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pop_up)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = (0.4 * dm.widthPixels).toInt()
        val height = (0.25 * dm.heightPixels).toInt()
        window.setLayout(width,height)
        val hasText: Boolean = intent.getBooleanExtra(getString(R.string.hasText),false)
        val setText: String = intent.getStringExtra(getString(R.string.setText))
        val hasTick: Boolean = intent.getBooleanExtra(getString(R.string.hasTick),false)
        val popUpText = findViewById<TextView>(R.id.pop_up_text)
        val tickImage = findViewById<ImageView>(R.id.tick_image)
        if(hasText){
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
public fun PopUp(act:Activity,hasText:Boolean = false,setText:String = "",hasTick:Boolean = false){
    val intent = Intent(act,PopUp()::class.java)
    intent.putExtra(act.getString(R.string.hasText),hasText)
    intent.putExtra(act.getString(R.string.hasTick),hasTick)
    intent.putExtra(act.getString(R.string.setText),setText)
    act.startActivity(intent)
}