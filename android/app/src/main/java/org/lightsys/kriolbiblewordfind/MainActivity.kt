package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.ImageView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        fab.isClickable=false

        val button1 = findViewById<ImageView>(R.id.button1)
        button1.setOnClickListener { view ->
            val intent = Intent(this,puzzleActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<ConstraintLayout>(R.id.button2)
        button2.setOnClickListener { view ->
            val intent = Intent(this,Empty::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<ConstraintLayout>(R.id.button3)
        button3.setOnClickListener { view ->
            val intent = Intent(this,Empty::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<ConstraintLayout>(R.id.button3)
        button4.setOnClickListener { view ->
            val intent = Intent(this,Empty::class.java)
            startActivity(intent)
        }
//        val button5 = findViewById<ConstraintLayout>(R.id.button3)
//        button5.setOnClickListener { view ->
//            val intent = Intent(this,Empty::class.java)
//            startActivity(intent)
//        }

    }


}
