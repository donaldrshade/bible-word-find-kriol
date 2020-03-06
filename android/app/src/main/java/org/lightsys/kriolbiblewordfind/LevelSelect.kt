package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_empty.*
import kotlinx.android.synthetic.main.how_to_play.*

class LevelSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val fab = findViewById<FloatingActionButton>(R.id.home)
        fab.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val levelTable = findViewById<TableLayout>(R.id.levelTable)


        val db = Database(this)
        val levels = db.getLevelList()

        //An integer that is the number of levels
        val numOfLevels = levels.size
        val comicSansFont : Typeface? = ResourcesCompat.getFont(this,R.font.comic_sans)
        val audioLevels = intArrayOf(9, 10, 11, 12, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44)
        val rowLayout = TableLayout.LayoutParams()
        rowLayout.setMargins(0,30,0,0)
        var row = TableRow(this)
        for (num in 1..numOfLevels){
            if (num %5 == 1){
                row =  TableRow(this)
                levelTable.addView(row, rowLayout)
            }
            if(audioLevels.contains(num)){
                val audioImage = ImageView(this)
                audioImage.setImageResource(R.drawable.headphones)
                audioImage.requestLayout();
                val params = TableRow.LayoutParams(30,30)
                audioImage.layoutParams = params
                row.addView(audioImage)

                //Send the player to the selected level
                audioImage.setOnClickListener{
                    setResult(num)
                    finish()
                }
            } else {
                val textView = TextView(this)
                textView.text = num.toString()
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.rgb(100,100,100))
                textView.typeface = comicSansFont
                row.addView(textView)


                //Send the player to the selected level
                textView.setOnClickListener{
                    textView.setTextColor(Color.BLACK)
                    setResult(num)
                    finish()
                }
            }

        }









    }

}