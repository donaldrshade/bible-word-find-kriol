package org.lightsys.kriolbiblewordfind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.TextView

// this class deals with the checkmark popup at the end of puzzles
class PopUp : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pop_up)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = (0.4 * dm.widthPixels).toInt()
        val height = (0.25 * dm.heightPixels).toInt()
        window.setLayout(width,height)
        //val hasText: Boolean = intent.getBooleanExtra(getString(R.string.hasText),false)
        //val setText: String = intent.getStringExtra(getString(R.string.setText))
        //val hasTick: Boolean = intent.getBooleanExtra(getString(R.string.hasTick),false)
        val popUpText = findViewById<TextView>(R.id.pop_up_text)
        val tickImage = findViewById<ImageView>(R.id.tick_image)
        val puzzleID: Int = intent.getIntExtra("puzzleID", 0)
        // we removed the "level won" popup so hasText is never true, it's always hasTick
        /*
        if(hasText){
            popUpText.visibility = TextView.VISIBLE
            popUpText.text = setText

            popUpText.setOnClickListener{
                finish()
                val intent = Intent(this,puzzleActivity::class.java)
                intent.putExtra(getString(R.string.puzzle_num),Database(this).getActivePuzzleNum())
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
        }
        */
//        else{
            popUpText.visibility = TextView.INVISIBLE
//        }
//        if(hasTick){
            tickImage.visibility = ImageView.VISIBLE

            tickImage.setOnClickListener{
                finish()
                val db = Database(this)
                //checks to see if the level of the puzzle you are trying to advance to is unlocked
                val intent : Intent
                if(db.getActiveLevel().id < db.getLevelIDFromPuzzleID(puzzleID)){
                    intent = Intent(this,LevelSelect::class.java)
                } else {
                    intent = Intent(this,PuzzleActivity::class.java)
                    intent.putExtra(getString(R.string.puzzle_num), puzzleID)
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
//        }else{
//            tickImage.visibility = ImageView.INVISIBLE
//        }
    }

    override fun onBackPressed() {
        // go back to home screen when back button is pressed
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
fun showPopUp(act:Activity, hasText:Boolean = false, setText:String = "", hasTick:Boolean = false, puzzleNum: Int = 0){
    val intent = Intent(act,PopUp()::class.java)
    intent.putExtra(act.getString(R.string.hasText),hasText)
    intent.putExtra(act.getString(R.string.hasTick),hasTick)
    intent.putExtra(act.getString(R.string.setText),setText)
    var nextPuzzleNum = puzzleNum + 1
    // TODO: hard-coded to 119, should be changed to numPuzzles
    if (puzzleNum == 119) {
        nextPuzzleNum = 1
    }
    intent.putExtra("puzzleID", nextPuzzleNum)

    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    act.startActivity(intent)
}