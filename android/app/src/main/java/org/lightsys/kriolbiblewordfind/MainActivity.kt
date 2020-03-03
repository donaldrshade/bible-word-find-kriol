package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this,puzzleActivity::class.java)
            startActivity(intent)
        }
    }


}
