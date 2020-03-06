package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
const val PLAY_LEVEL = 10
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
//        fab.setOnClickListener {
//            PopUp(this,hasText = true,setText = "Hi, from Colorado!")
//        }
        fab.isClickable=false

        val playButton = findViewById<ImageView>(R.id.play_button)
        playButton.setOnClickListener {
            val intent = Intent(this,puzzleActivity::class.java)
            intent.putExtra(getString(R.string.puzzle_num),Database(this).getActivePuzzleNum())
            startActivity(intent)
        }
        val howToPlayButton = findViewById<ConstraintLayout>(R.id.how_to_play_button)
        howToPlayButton.setOnClickListener {
            val intent = Intent(this,puzzleActivity::class.java)
            startActivityForResult(intent,PLAY_LEVEL)
        }
        val gameLevelButton = findViewById<ConstraintLayout>(R.id.game_level_button)
        gameLevelButton.setOnClickListener {
            val intent = Intent(this,LevelSelect::class.java)
            startActivity(intent)
        }
        val listenBibleButton = findViewById<ConstraintLayout>(R.id.listen_bible_button)
        listenBibleButton.setOnClickListener {
            val intent = Intent(this,ListenActivity::class.java)
            startActivity(intent)
        }
        val watchBibleButton = findViewById<ConstraintLayout>(R.id.watch_bible_button)
        watchBibleButton.setOnClickListener {
            val intent = Intent(this,WatchActivity::class.java)
            startActivity(intent)
        }
        val developerButton = findViewById<ConstraintLayout>(R.id.developer_button)
        developerButton.setOnClickListener {
            val intent = Intent(this,Empty::class.java)
            startActivity(intent)
        }
    }
}