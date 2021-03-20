package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val LEVEL_SELECT = 1
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
            playButton.setImageResource(R.drawable.plei_buton_active)
            val intent = Intent(this,puzzleActivity::class.java)
            intent.putExtra(getString(R.string.puzzle_num),Database(this).getActivePuzzleNum())
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
//        val howToPlayButton = findViewById<ConstraintLayout>(R.id.how_to_play_button)
//        howToPlayButton.setOnClickListener {
//            val intent = Intent(this,Empty::class.java)
//            startActivity(intent)
//        }
        val gameLevelButton = findViewById<ConstraintLayout>(R.id.game_level_button)
        gameLevelButton.setOnClickListener {
            val intent = Intent(this,LevelSelect::class.java)
            startActivityForResult(intent, LEVEL_SELECT)
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
        val noise = findViewById<Switch>(R.id.sound_switch)
        val sp = this.getSharedPreferences(getString(R.string.points_file_key), Context.MODE_PRIVATE)
        noise.isChecked = sp.getBoolean(getString(R.string.points_file_key),false)
        noise.setOnCheckedChangeListener { buttonView, isChecked ->
            val edit = sp.edit()
            edit.putBoolean(getString(R.string.SOUNDS),isChecked)
            edit.apply()
        }
        val developerButton = findViewById<ConstraintLayout>(R.id.developer_button)
        developerButton.setOnClickListener {
            val intent = Intent(this,Empty::class.java)
            startActivity(intent)
        }
    }

    //Changes the plei button back to the default picture
    override fun onResume() {
        super.onResume()
        val playButton = findViewById<ImageView>(R.id.play_button)
        playButton.setImageResource(R.drawable.plei_buton)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LEVEL_SELECT){
            val intent = Intent(this,puzzleActivity::class.java)
            intent.putExtra(getString(R.string.puzzle_num),resultCode)
            startActivity(intent)
        }
    }
}