package org.lightsys.kriolbiblewordfind
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puzzle.*
import kotlinx.android.synthetic.main.nav_header_main.*


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



        val intent = intent;
        val pnum = intent.getIntExtra(getString(R.string.puzzle_num),-1);

        var puzzle = Puzzle();
        var db = Database(this)
        puzzle = db.getPuzzle(pnum)
       var puzzleSize = puzzle.size;
        puzzleSize = 3;//TODO

        val gridSizer = findViewById<ConstraintLayout>(R.id.gridSizer);
        val cset = ConstraintSet();

        val height = gridSizer.height;
        val split = height / puzzleSize;

        var idArr : Array<Int?>
        idArr = arrayOfNulls(puzzleSize*puzzleSize);

        for (r in 0 until puzzleSize) {
            for (c in 0 until puzzleSize) {
                var textView = TextView(this)
                textView.id = 1000+r*puzzleSize+c;
                idArr[r*puzzleSize+c] = textView.id;
                textView.text = "${r}${c}"

                textView.gravity = Gravity.CENTER
                //textView.setBackgroundColor(40)
                val lp = ConstraintLayout.LayoutParams(ConstraintSet.MATCH_CONSTRAINT, ConstraintSet.MATCH_CONSTRAINT)


                gridSizer!!.addView(textView, lp)
               /* textView.setOnTouchListener( ) {v: View, m: MotionEvent ->
                    when(m.actionMasked){
                        MotionEvent.ACTION_DOWN -> {
                            this.setStartLetter(textView);
                        }
                        MotionEvent.ACTION_UP -> {
                            this.setEndLetter(textView);
                        }
                    }
                    true;
                }*/
            }
        }

        cset.clone(gridSizer);

        val dimensionBox = R.id.gridSizer
        cset.setDimensionRatio(dimensionBox, ("$puzzleSize:$puzzleSize"));
        for (r in 0 until puzzleSize) {
            val intarr = IntArray(puzzleSize);
            for (c in 0 until puzzleSize) {
                intarr[c] = 1000+r*puzzleSize + c;
                val id = 1000+r*puzzleSize + c
                cset.setDimensionRatio(id, "1:1")
                if(r==0){
                    cset.connect(id, ConstraintSet.TOP, dimensionBox, ConstraintSet.TOP);
                } else {
                    cset.connect(id, ConstraintSet.TOP, 1000+(r-1)*puzzleSize+c, ConstraintSet.BOTTOM)
                }


            }
            cset.createHorizontalChain(dimensionBox, ConstraintSet.LEFT, dimensionBox, ConstraintSet.RIGHT,
                intarr, null, ConstraintSet.CHAIN_SPREAD)
        }
        cset.applyTo(gridSizer)















        //String that contains the banner name
        val levelBanner = "eijah";//TODO

        //Changing the banner
        val bannerRes: Resources = resources;
        val bannerResID = bannerRes.getIdentifier(levelBanner, "drawable", packageName);
        val levelBackground = findViewById<ConstraintLayout>(R.id.backgound)
        val rnds = (0..10).random()

        story_title_banner_image.setImageResource(bannerResID);

        when (rnds){
            0-> levelBackground.setBackgroundColor(Color.rgb(0,188,212));
            1-> levelBackground.setBackgroundColor(Color.rgb(152,228,146));
            2-> levelBackground.setBackgroundColor(Color.rgb(220,195,154));
            3-> levelBackground.setBackgroundColor(Color.rgb(173,166,227));
            4-> levelBackground.setBackgroundColor(Color.rgb(214,174,236));
            5-> levelBackground.setBackgroundColor(Color.rgb(180,218,217));
            6-> levelBackground.setBackgroundColor(Color.rgb(218,180,210));
            7-> levelBackground.setBackgroundColor(Color.rgb(185,209,175));
            8-> levelBackground.setBackgroundColor(Color.rgb(166,225,195));
            9-> levelBackground.setBackgroundColor(Color.rgb(20,180,180));
            else -> {levelBackground.setBackgroundColor(Color.rgb(20,180,180));}
        }

    }


    /*fun setStartLetter( box: TextView){
       // box.setText("Start");
        //box.setText(box.left.toString())
    }

    fun setEndLetter(box : TextView){
        box.setText(box.top.toString());
    }
*/
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