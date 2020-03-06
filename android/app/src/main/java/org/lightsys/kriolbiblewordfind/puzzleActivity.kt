package org.lightsys.kriolbiblewordfind
import PuzzleEngine
import Word
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puzzle.*
import java.lang.Math.floor


class puzzleActivity : AppCompatActivity() {
    var puzzleSize = 0
    var letters = arrayOf<TextView?>()
    var wordList = arrayOf<Word?>()
    var wordCounter = 0
    var isAudioPuzzle = false
    lateinit var db:Database
    lateinit var puzzleEngine:PuzzleEngine
    lateinit var sp:SharedPreferences

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_puzzle)

        //Create canvas to track swipes
        var canvas = DrawingView(this, null, this)
        var params = LinearLayout.LayoutParams(
            gridSizer.layoutParams.width,
            gridSizer.layoutParams.height
        )
        canvas.id = 999
        gridSizer.addView(canvas, params)
        canvas.bringToFront()

        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener { view ->
            finish()
        }

        val intent = intent
        val comicSansFont : Typeface? = ResourcesCompat.getFont(this,R.font.comic_sans_b)
        //TODO: Get pnum from strings file
        val pnum = intent.getIntExtra(getString(R.string.puzzle_num),-1)

        sp = this.getSharedPreferences(getString(R.string.points_file_key), Context.MODE_PRIVATE)
        val boatCount = sp.getInt(getString(R.string.boat_key),0)
        val fishCount = sp.getInt(getString(R.string.fish_key),0)
        val breadCount = sp.getInt(getString(R.string.bread_key),0)
        val boatView = findViewById<TextView>(R.id.boatScoreNumber)
        boatView.setText(boatCount.toString())
        val fishView = findViewById<TextView>(R.id.fishScoreNumber)
        fishView.setText(fishCount.toString())
        val breadView = findViewById<TextView>(R.id.breadScoreNumber)
        breadView.setText(breadCount.toString())


        //Initiate Database and load puzzle engine
        db = Database(this)
        var puzzle = db.getPuzzle(pnum)
        var levelnum = puzzle.level_id
        puzzleEngine = PuzzleEngine(puzzle, this)
        var puzzleGrid = puzzleEngine.grid
        wordList = puzzleEngine.getWords()

        //Load words into wordbank, only populate it if not audio puzzle
        wordCounter = wordList.size
        isAudioPuzzle = db.isAudioPuzzle(puzzleEngine.puzzle.id)
        if(isAudioPuzzle){

        } else {
            for(w in 0 until wordList.size){
                var textView = TextView(this)
                var params = LinearLayout.LayoutParams(
                    wordBank.layoutParams.width,
                    wordBank.layoutParams.height
                )
                textView.textSize= 20F
                textView.text = wordList[w]!!.word
                textView.id = w + 2000
                textView.typeface = comicSansFont
                wordBank.addView(textView, params)
            }
        }

        val gridSizer = findViewById<ConstraintLayout>(R.id.gridSizer)
        val cset = ConstraintSet()

        puzzleSize = puzzle.size
        letters = arrayOfNulls(puzzleSize*puzzleSize)
        for (r in 0 until puzzleSize) {
            for (c in 0 until puzzleSize) {
                var textView = TextView(this)
                textView.id = 1000+r*puzzleSize+c
                letters[r*puzzleSize+c] = textView
                textView.text = puzzleGrid[r][c].toString()
                textView.typeface = comicSansFont
                textView.gravity = Gravity.CENTER
                //textView.setBackgroundColor(40)
                val lp = ConstraintLayout.LayoutParams(ConstraintSet.MATCH_CONSTRAINT, ConstraintSet.MATCH_CONSTRAINT)


                gridSizer!!.addView(textView, lp)
               /* textView.setOnTouchListener( ) {v: View, m: MotionEvent ->
                    when(m.actionMasked){
                        MotionEvent.ACTION_DOWN -> {
                            this.setStartLetter(textView)
                        }
                        MotionEvent.ACTION_UP -> {
                            this.setEndLetter(textView)
                        }
                    }
                    true
                }*/
            }
        }

        cset.clone(gridSizer)

        val dimensionBox = R.id.gridSizer
        cset.setDimensionRatio(dimensionBox, ("$puzzleSize:$puzzleSize"))
        for (r in 0 until puzzleSize) {
            val intarr = IntArray(puzzleSize)
            for (c in 0 until puzzleSize) {
                intarr[c] = 1000+r*puzzleSize + c
                val id = 1000+r*puzzleSize + c
                cset.setDimensionRatio(id, "1:1")
                if(r==0){
                    cset.connect(id, ConstraintSet.TOP, dimensionBox, ConstraintSet.TOP)
                } else {
                    cset.connect(id, ConstraintSet.TOP, 1000+(r-1)*puzzleSize+c, ConstraintSet.BOTTOM)
                }
            }
            cset.createHorizontalChain(dimensionBox, ConstraintSet.LEFT, dimensionBox, ConstraintSet.RIGHT,
                intarr, null, ConstraintSet.CHAIN_SPREAD)
        }
        cset.applyTo(gridSizer)

        //String that contains the banner name
        val currentLevel = db.getLevel(levelnum)

        val levelName = currentLevel.title
        val levelBanner = currentLevel.picture


        //Setting the level title
        val levelTitleText = findViewById<TextView>(R.id.levelTitle)
        levelTitleText.textSize = 20F
        levelTitleText.setBackgroundColor(Color.argb(150,200,255,255))
        levelTitleText.setText(levelName)


        //Setting the banner
        val bannerRes: Resources = resources;
        val bannerResID = bannerRes.getIdentifier(levelBanner, "drawable", packageName);
        val levelBackground = findViewById<ConstraintLayout>(R.id.backgound)
        val rnds = (0..10).random()

        story_title_banner_image.setImageResource(bannerResID);

        when (rnds){
            0-> levelBackground.setBackgroundColor(Color.rgb(0,188,212))
            1-> levelBackground.setBackgroundColor(Color.rgb(152,228,146))
            2-> levelBackground.setBackgroundColor(Color.rgb(220, 195, 154))
            3->  levelBackground.setBackgroundColor(Color.rgb(173, 166, 227))
            4-> levelBackground.setBackgroundColor(Color.rgb(214,174,236));
            5-> levelBackground.setBackgroundColor(Color.rgb(180,218,217));
            6-> levelBackground.setBackgroundColor(Color.rgb(218,180,210));
            7-> levelBackground.setBackgroundColor(Color.rgb(185,209,175));
            8-> levelBackground.setBackgroundColor(Color.rgb(166,225,195));
            9-> levelBackground.setBackgroundColor(Color.rgb(20,180,180));
            else -> {levelBackground.setBackgroundColor(Color.rgb(20,180,180));}
        }
    }

    fun getGridCell(x: Float, y: Float) : TextView?{
        return letters[getGridCellIndex(x, y)]
    }

    private fun getGridCellIndex(x: Float, y: Float) : Int {
        val height = gridSizer.height
        val split = height / puzzleSize

        var row = floor(y.toDouble() / split).toInt()
        var col = floor(x.toDouble() / split).toInt()
        if (row < 0) row = 0
        if (col < 0) col = 0
        row = Math.min(row, puzzleSize - 1)
        col = Math.min(col, puzzleSize - 1)
        return row * puzzleSize + col
    }

    fun isValidWord(startX: Float, startY: Float, endX: Float, endY: Float) : Boolean{
        var ind1 = getGridCellIndex(startX, startY)
        var row1 = (ind1 / puzzleSize)
        var col1 = ind1 % puzzleSize

        var ind2 = getGridCellIndex(endX, endY)
        var row2 = (ind2 / puzzleSize)
        var col2 = ind2 % puzzleSize

        for(i in 0 until wordList.size){
            val word = wordList[i]
            if(word == null) continue
            if(word!!.getStartPt()[0] == row1 && word.getStartPt()[1] == col1 && word.getEndPt()[0] == row2 && word.getEndPt()[1] == col2){
                wordList[i] = null
                gainBread()

                //Cross off word from word bank
                if(isAudioPuzzle){

                } else {
                    var wordText = findViewById<TextView>(i + 2000)
                    wordText.paintFlags = wordText.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG
                }

                //Check how many words left, if all discovered, win level
                wordCounter--
                if(wordCounter == 0){
                    gainFish()
                    //TODO: winPuzzle() //Not sure if this function will be necessary
                    val levelComplete = db.markPuzzleCompleted(puzzleEngine.puzzle.id)
                    if(levelComplete){
                        gainBoat()
                    }
                    //
                }
                return true
            }
        }
        return false
    }

    //Subtracts 1 from the boat number when tapped
    //TODO: The boat number is set to 5 by default and must be changed
    fun useBoat(view: View) {
        var boatString = boatScoreNumber.text.toString()
        var boatInt = boatString.toInt()

        if (boatInt > 2){
            boatInt -= 3
            boatScoreNumber.text = boatInt.toString()
            //TODO: Spend 3 boats to unlock a level
        }
    }

    //Subtracts 1 from the fish number when tapped
    //TODO: The fish number is set to 5 by default and must be changed
    fun useFish(view: View) {
        var fishString = fishScoreNumber.text.toString()
        var fishInt = fishString.toInt()

        if (fishInt > 0){
            fishInt--
            fishScoreNumber.text = fishInt.toString()
            //TODO: Reveal an entire word
        }
    }

    //Subtracts 1 from the bread number when tapped
    //TODO: The bread number is set to 5 by default and must be changed
    fun useBread(view: View) {
        var breadString = breadScoreNumber.text.toString()
        var breadInt = breadString.toInt()

        if (breadInt > 0) {
            breadInt--
            breadScoreNumber.text = breadInt.toString()
            //TODO: Reveal one random letter
        }
    }

    fun gainBoat(){
       var boatString = boatScoreNumber.text.toString()
        var boatInt = boatString.toInt()
        boatInt++
        boatScoreNumber.text = boatInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.boat_key),boatInt)
        edit.commit()
    }

    fun gainFish(){
        var fishString = fishScoreNumber.text.toString()
        var fishInt = fishString.toInt()

        fishInt++
        fishScoreNumber.text = fishInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.fish_key),fishInt)
        edit.commit()
    }

    fun gainBread(){
       var breadString = breadScoreNumber.text.toString()
        var breadInt = breadString.toInt()

        breadInt++
        breadScoreNumber.text = breadInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.bread_key),breadInt)
        edit.commit()
    }
}