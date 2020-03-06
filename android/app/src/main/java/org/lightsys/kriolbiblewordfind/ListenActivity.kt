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

import kotlinx.android.synthetic.main.activity_listen.*

class ListenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_listen)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener {
            finish()
        }
        val listen_bible_link_1 = findViewById<ConstraintLayout>(R.id.listen_bible_link_1)
        listen_bible_link_1.setOnClickListener {
            val url = "https://play.google.com/store/apps/developer?id=Vernacular+Scriptures+Australia"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }
        val listen_bible_link_2 = findViewById<ConstraintLayout>(R.id.listen_bible_link_2)
        listen_bible_link_2.setOnClickListener {
            val url = "https://aboriginalbibles.org.au/kriol/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }
        val listen_bible_link_3 = findViewById<ConstraintLayout>(R.id.listen_bible_link_3)
        listen_bible_link_3.setOnClickListener {
            val url = "https://ebible.org/rop/mp3/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }
    }

}
