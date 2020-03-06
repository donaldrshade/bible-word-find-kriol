package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager

import kotlinx.android.synthetic.main.activity_empty.*

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


        val db = Database(this)
        val levels = db.getLevelList()

        //An integer that is the number of levels
        val numOfLevels = levels.size







    }

}