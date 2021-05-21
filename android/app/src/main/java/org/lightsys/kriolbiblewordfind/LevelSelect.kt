package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
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

        val comicSansFont : Typeface? = ResourcesCompat.getFont(this,R.font.comic_sans_b)
        val audioLevels = intArrayOf(9, 10, 11, 12, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44)
        val rowLayout = TableLayout.LayoutParams()
        rowLayout.setMargins(0,30,0,0)
        var row = TableRow(this)

        val puzzles = db.getPuzzleList()
        val numPuzzles = puzzles.size
        var prevLevel = -2
        var puzzleNumInLevel = 1
        for (num in 1..numPuzzles){

            // prev code for making rows
            /*
            if (num %5 == 1){
                row =  TableRow(this)
                levelTable.addView(row, rowLayout)
            }
            */


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
            //Implements 1-1, 2-2, 7-4, etc format
            val levelID = db.getLevelIDFromPuzzleID(num)
            if(prevLevel != levelID){

                row = TableRow(this)
                levelTable.addView(row, rowLayout)
                puzzleNumInLevel = 1
                prevLevel = levelID

             /*   textView.text = (levelID.toString() + '.')
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.BLACK)
                if (db.getPuzzle(num).completed) {
                    textView.setTextColor(Color.GREEN)
                }
                textView.typeface = comicSansFont
                row.addView(textView) */
            }

            textView.text = (levelID.toString() + "-" + puzzleNumInLevel.toString())
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.BLACK)
            if (db.getPuzzle(num).completed) {
                textView.setTextColor(Color.GREEN)
            }
            textView.typeface = comicSansFont

            //colors locked levels red
            if (!db.getLevel(levelID).completed && db.getActiveLevel().id != levelID){
                textView.setTextColor(Color.RED)
            }

            row.addView(textView)

            //Send the player to the selected puzzle, if the level is unlocked
            textView.setOnClickListener {
                val lastLevel = db.getActiveLevel()
                println(lastLevel.id)
                //implements level locking
                if (levelID <= lastLevel.id) {
                    textView.setTextColor(Color.BLUE)
                    val intent = Intent(this, puzzleActivity::class.java)
                    intent.putExtra(getString(R.string.puzzle_num), num)
                    //a roundabout way of displaying the level-puzzle value on the puzzle activity
                    val localPuzzleNum = textView.text
                    intent.putExtra(getString(R.string.local_puzzle_num), localPuzzleNum)

                    startActivity(intent)
                }
            }
            puzzleNumInLevel++
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

            //allows access to the next level
            db.markLevelCompleted(curLevel)

            //refreshes the colors
            var puzzleNum = 1
            for(i in 0..levelTable.childCount){
                val levelTableRow = levelTable.getChildAt(i) as? TableRow
                if(levelTableRow != null) {
                    for (j in 0..levelTableRow.childCount) {
                        val text = levelTableRow.getChildAt(j) as? TextView
                        if (text != null) {
                            val levelID = i + 1
                            //all go to black...
                            text.setTextColor(Color.BLACK)
                            //and completed are green...
                            if (db.getPuzzle(puzzleNum).completed) {
                                text.setTextColor(Color.GREEN)
                            }
                            //and locked are red
                            if (!db.getLevel(levelID).completed && db.getActiveLevel().id != levelID) {
                                text.setTextColor(Color.RED)
                            }
                            puzzleNum++
                        }
                    }
                }
            }




            /*
            var pList: ArrayList<Puzzle> = db.getLvlPuzzleList(curPuzzle.level_id)
            for (puz in pList) {
                puz.completed = true
            }
            */

            val edit = sp.edit()
            edit.putInt(getString(R.string.boat_key),boatInt)
            edit.commit()
        }
    }

}