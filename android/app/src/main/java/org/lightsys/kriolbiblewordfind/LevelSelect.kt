package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
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

        val recyclerView = findViewById<RecyclerView>(R.id.levellist)


        val db = Database(this)
        val levels = db.getLevelList()

        //An integer that is the number of levels
        val numOfLevels = levels.size

/*

        for (num in 0..numOfLevels){
            val textView = TextView(this)
            textView.setText("Test Tex")
            recyclerView!!.addView(textView)
        }

*/





    }

}