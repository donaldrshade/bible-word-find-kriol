package org.lightsys.kriolbiblewordfind

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.ImageViewCompat
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puzzle.*

class puzzleActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_puzzle)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            finish()
        }

        val intent = getIntent()
        val level = intent.getIntExtra(getString(R.string.level_num),-1)


        //image_name should contain the name of the banner that matches the current level
        //TODO: I need to select the picture from a string
        val imageName = R.drawable.fespida3
        val banner  = findViewById<ImageView>(R.id.story_title_banner_image)


        //Changing the banner to the banner specified.
        banner.setImageResource(imageName)
    }

    //Subtracts 1 from the boat number when tapped.
    //TODO: The boat number is set to 5 by default and must be changed
    fun boatClicked(view: View) {
        val boatView  = findViewById<TextView>(R.id.boatScoreNumber)

        var boatString = boatView.text.toString();
        var boatInt = boatString.toInt()

        if (boatInt > 0){
            boatInt -= 1
            boatString = boatInt.toString()
            boatView.text = boatString
        }
    }

    //Subtracts 1 from the fish number when tapped
    //TODO: The fish number is set to 5 by default and must be changed
    fun fishClicked(view: View) {
        val fishView  = findViewById<TextView>(R.id.fishScoreNumber)

        var fishString = fishView.text.toString();
        var fishInt = fishString.toInt()

        if (fishInt > 0){
            fishInt -= 1
            fishString = fishInt.toString()
            fishView.text = fishString
        }

    }

    //Subtracts 1 from the bread number when tapped
    //TODO: The bread number is set to 5 by default and must be changed
    fun breadClicked(view: View) {
        val breadView  = findViewById<TextView>(R.id.breadScoreNumber)

        var breadString = breadView.text.toString();
        var breadInt = breadString.toInt()

        if (breadInt > 0){
            breadInt -= 1
            breadString = breadInt.toString()
            breadView.text = breadString
        }
    }
}