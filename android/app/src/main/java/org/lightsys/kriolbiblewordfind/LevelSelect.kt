package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_empty.*
import kotlinx.android.synthetic.main.activity_level_select.*
import kotlinx.android.synthetic.main.activity_puzzle.*
import kotlinx.android.synthetic.main.how_to_play.*

class LevelSelect : AppCompatActivity() {
    lateinit var sp : SharedPreferences
    lateinit var db : Database
    var num = 0;


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

        val boat = findViewById<ImageView>(R.id.levelSelectBoat)
        boat.setOnClickListener {
            useBoat()
        }

        sp = this.getSharedPreferences(getString(R.string.points_file_key), Context.MODE_PRIVATE)
        levelSelectBoatText.text = sp.getInt(getString(R.string.boat_key),0).toString()
        db = Database(this)
        val levels = db.getLevelList()



        //An integer that is the number of levels
        val numOfLevels = levels.size
        val comicSansFont : Typeface? = ResourcesCompat.getFont(this,R.font.comic_sans_b)
        val audioLevels = intArrayOf(9, 10, 11, 12, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44)
        val rowLayout = TableLayout.LayoutParams()
        rowLayout.setMargins(0,30,0,0)
        var row = TableRow(this)
        for (num in 1..numOfLevels){
            if (num %5 == 1){
                row =  TableRow(this)
                levelTable.addView(row, rowLayout)
            }
            // I want level numbers instead of headphones for audio puzzles
/*            if(audioLevels.contains(num)){

                val audioImage = ImageView(this)
                audioImage.setImageResource(R.drawable.headphones)
                audioImage.requestLayout();
                val params = TableRow.LayoutParams(30,30)
                audioImage.layoutParams = params
                row.addView(audioImage)

                //Enable button if the level is unlocked
                    //Send the player to the selected level
                audioImage.setOnClickListener {

                    val lastLevel = db.getActiveLevel()

                    if (lastLevel.id >= 0) {
                        audioImage.setImageResource(R.drawable.headphones_active)
                        setResult(num)
                        finish()
                    }
                }
            } else {
 */
                val textView = TextView(this)
                textView.text = num.toString()
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.rgb(100,100,100))
                textView.typeface = comicSansFont
                row.addView(textView)


                //Send the player to the selected level
                textView.setOnClickListener{
                    val lastLevel = db.getActiveLevel()
                    //Enable button if the level is unlocked
                    if (lastLevel.id >= 0) {
                        textView.setTextColor(Color.GREEN)
                        setResult(num)
                        finish()
//                    }
                }
            }

        }

    }

    override fun onBackPressed() {
        // go back to home screen when back button is pressed
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    //Subtracts 3 from the boat number when tapped
    fun useBoat() {
        var boatString = levelSelectBoatText.text.toString()
        var boatInt = boatString.toInt()

        if (boatInt > 2){
            boatInt -= 3
            levelSelectBoatText.text = boatInt.toString()
            //TODO: Spend 3 boats to unlock a level
            val curLevel = db.getActiveLevel().id

            var curPuzzle = db.getActivePuzzle(curLevel)
            while(curPuzzle.level_id == curLevel){
                db.markPuzzleCompleted(curPuzzle.id)
                curPuzzle = db.getActivePuzzle(curLevel)
            }


           db.markLevelCompleted(curLevel)



            val edit = sp.edit()
            edit.putInt(getString(R.string.boat_key),boatInt)
            edit.commit()
        }
    }

}