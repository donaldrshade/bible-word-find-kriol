package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)


        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this,puzzleActivity::class.java)
            startActivity(intent)
        }
        fab.isClickable=false

        val button1 = findViewById<ImageView>(R.id.button1)
        button1.setOnClickListener { view ->
            val intent = Intent(this,puzzleActivity::class.java)
            startActivity(intent)
        }

    }


}
