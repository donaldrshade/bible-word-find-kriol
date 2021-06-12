package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_empty.*

class Empty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { _ ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // go back to home screen when back button is pressed
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}
