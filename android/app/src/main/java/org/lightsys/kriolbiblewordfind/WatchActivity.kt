package org.lightsys.kriolbiblewordfind

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView

import kotlinx.android.synthetic.main.activity_listen.*

class WatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_watch)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener {
            finish()
        }
        val watch_bible_link_button = findViewById<ImageView>(R.id.watch_bible_link)
        watch_bible_link_button.setOnClickListener{
            val url = "https://www.youtube.com/user/AboriginalBibles"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }

    }

}
