package org.lightsys.kriolbiblewordfind
import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puzzle.*



class puzzleActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_puzzle)

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            finish()
        }

        /*
        for (r in size)
            for ( c in size)
                val text = //make a new text thing
                text.setOnTouchListener {make a thing for on motion_down and motion_up}
                //set position
         */

        val intent = intent;
        val level = intent.getIntExtra(getString(R.string.level_num),-1);

        //String that contains the banner name
        val levelBanner = "eijah";

        //Changing the banner
        val res: Resources = resources;
        val resID = res.getIdentifier(levelBanner, "drawable", packageName);
        story_title_banner_image.setImageResource(resID);
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