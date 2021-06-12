package org.lightsys.kriolbiblewordfind

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface

import android.graphics.Paint
import android.media.MediaPlayer
import android.nfc.FormatException

import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_puzzle.*
import kotlin.math.abs
import kotlin.math.min


class PuzzleActivity : AppCompatActivity() {
    var puzzleSize = 0
    var letters = arrayOf<TextView?>()
    var wordList = arrayOf<Word?>()
    var wordCounter = 0
    var isAudioPuzzle = false
    lateinit var db:Database
    lateinit var puzzleEngine: PuzzleEngine
    lateinit var sp:SharedPreferences
    lateinit var canvas : DrawingView
    lateinit var breadHighlights : Array<BooleanArray>
    lateinit var media:MediaPlayer
    lateinit var soundEffect:MediaPlayer

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = DrawingView(this, null, this)

        //Set Activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_puzzle)

        //Create canvas to track swipes

        val gridSizer = findViewById<ConstraintLayout>(R.id.gridSizer)
        gridSizer.bringToFront()
        val cset = ConstraintSet()



        val params = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        canvas.id = 999
        gridSizer.addView(canvas, params)
        canvas.bringToFront()

        //Set Home Button
        val fab = findViewById<FloatingActionButton>(R.id.home_fab)
        fab.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val intent = intent
        val comicSansFont : Typeface? = ResourcesCompat.getFont(this,R.font.comic_sans_b)
        val pnum = intent.getIntExtra(getString(R.string.puzzle_num),-1)

        sp = this.getSharedPreferences(getString(R.string.points_file_key), Context.MODE_PRIVATE)
        val boatCount = sp.getInt(getString(R.string.boat_key),0)
        val fishCount = sp.getInt(getString(R.string.fish_key),0)
        val breadCount = sp.getInt(getString(R.string.bread_key),0)
        val boatView = findViewById<TextView>(R.id.boatScoreNumber)
        boatView.text = boatCount.toString()
        val fishView = findViewById<TextView>(R.id.fishScoreNumber)
        fishView.text = fishCount.toString()
        val breadView = findViewById<TextView>(R.id.breadScoreNumber)
        breadView.text = breadCount.toString()

        //Initiate Database and load puzzle engine
        db = Database(this)
        val puzzle = db.getPuzzle(pnum)
        val levelnum = puzzle.level_id
        puzzleEngine = PuzzleEngine(puzzle, this)

        val puzzleGrid = puzzleEngine.grid
        wordList = puzzleEngine.getWords()
        breadHighlights = Array(puzzleGrid.size){BooleanArray(puzzleGrid.size)}

        //Load words into wordbank, only populate it if not audio puzzle
        //If audio puzzle, add play/pause button and word counter
        wordCounter = 0
        isAudioPuzzle = db.isAudioPuzzle(puzzleEngine.puzzle.id)

        val wordBank = findViewById<TableLayout>(R.id.wordBank)

        if(isAudioPuzzle){
            val row = TableRow(this)
            val rowParams = TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,100)
            wordBank.addView(row)

            //creates and designs Headphone icon
            val audioViewHeadphones = ImageButton(this)
            audioViewHeadphones.layoutParams = rowParams
            audioViewHeadphones.setImageResource(R.drawable.headphones)
            audioViewHeadphones.setBackgroundResource(0)
            audioViewHeadphones.scaleX = 2F
            audioViewHeadphones.scaleY = 2F
            audioViewHeadphones.adjustViewBounds = true
            audioViewHeadphones.id = 2000

            //creates and designs Play icon
            val audioViewPlay = ImageButton(this)
            audioViewPlay.layoutParams = rowParams
            audioViewPlay.setImageResource(R.drawable.ic_menu_play)
            audioViewPlay.setBackgroundResource(0)
            audioViewPlay.setColorFilter(Color.BLACK)
            audioViewPlay.scaleX = 2.0F
            audioViewPlay.scaleY = 2.0F
            audioViewPlay.adjustViewBounds = true
            audioViewPlay.id = 2002

            //Initialize media player
            try {
                media = createMedia(puzzle.audioFile)
            } catch (e : FormatException) {
                Toast.makeText(getApplicationContext(), "Incorrect file format", Toast.LENGTH_SHORT).show()
                finish()
            } catch(e : Resources.NotFoundException){
                Toast.makeText(getApplicationContext(), puzzle.audioFile, Toast.LENGTH_SHORT).show()
                finish()
            }

            //If audio is playing, clicking audio button pauses it
            //Otherwise, play audio
            audioViewHeadphones.setOnClickListener {
                if(media.isPlaying()){
                    media.pause()
                } else {
                    media.start()
                }
            }
            audioViewPlay.setOnClickListener {
                if(media.isPlaying()){
                    media.pause()
                } else {
                    media.start()
                }
            }

            //creates and designs wordbank
            val wordsLeft = TextView(this)
            wordsLeft.textSize = 20F
            wordsLeft.text = "$wordCounter / ${wordList.size}"
            wordsLeft.layoutParams = rowParams
            wordsLeft.id = 2001
            wordsLeft.typeface = comicSansFont
            wordsLeft.gravity = 1400


            row.addView(audioViewHeadphones)
            row.addView(audioViewPlay)
            row.addView(wordsLeft)
        } else {
            var row = TableRow(this)
            for(w in wordList.indices){
                if(w % 3 == 0){
                    row = TableRow(this)
                    wordBank.addView(row)
                }
                val textView = TextView(this)
                textView.textSize= 20F
                textView.gravity = Gravity.CENTER
                textView.text = wordList[w]!!.word
                textView.id = w + 2000
                textView.typeface = comicSansFont
                textView.setTextColor(Color.BLACK)

                row.addView(textView)
            }
        }

        //creates letter grid
        puzzleSize = puzzle.size
        letters = arrayOfNulls(puzzleSize*puzzleSize)
        for (r in 0 until puzzleSize) {
            for (c in 0 until puzzleSize) {
                val textView = TextView(this)
                textView.id = 1000+r*puzzleSize+c
                letters[r*puzzleSize+c] = textView
                textView.text = puzzleGrid[r][c].toString()
                textView.typeface = comicSansFont
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.BLACK)
                //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,20.0F)
                //textView.setBackgroundColor(40)
                val lp = ConstraintLayout.LayoutParams(ConstraintSet.MATCH_CONSTRAINT, ConstraintSet.MATCH_CONSTRAINT)

                gridSizer!!.addView(textView, lp)
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
        val bannerRes: Resources = resources
        val bannerResID = bannerRes.getIdentifier(levelBanner, "drawable", packageName)
        val levelBackground = findViewById<ConstraintLayout>(R.id.puzzleConstraintLayout)
        val rnds = (0..10).random()

        story_title_banner_image.setImageResource(bannerResID)

        //setting the level number
        val levelNumber = findViewById<TextView>(R.id.levelPuzzleNumber)

        val puzzleLocal = db.getPuzzleIndInLevel(puzzleEngine.puzzle.id)
        levelNumber.text = "${puzzleEngine.puzzle.level_id}-$puzzleLocal"


        when (rnds){
            0-> levelBackground.setBackgroundColor(Color.rgb(0,188,212))
            1-> levelBackground.setBackgroundColor(Color.rgb(152,228,146))
            2-> levelBackground.setBackgroundColor(Color.rgb(220, 195, 154))
            3->  levelBackground.setBackgroundColor(Color.rgb(173, 166, 227))
            4-> levelBackground.setBackgroundColor(Color.rgb(214,174,236))
            5-> levelBackground.setBackgroundColor(Color.rgb(180,218,217))
            6-> levelBackground.setBackgroundColor(Color.rgb(218,180,210))
            7-> levelBackground.setBackgroundColor(Color.rgb(185,209,175))
            8-> levelBackground.setBackgroundColor(Color.rgb(166,225,195))
            9-> levelBackground.setBackgroundColor(Color.rgb(20,180,180))
            else -> {levelBackground.setBackgroundColor(Color.rgb(20,180,180))}
        }
    }

    override fun onBackPressed() {
        // go back to home screen when back button is pressed
        val intent = Intent(this,LevelSelect::class.java)
        startActivity(intent)
    }

    fun getGridCell(x: Float, y: Float) : TextView?{
        return letters[getGridCellIndex(x, y)]
    }

    private fun getGridCellIndex(x: Float, y: Float) : Int {
        val height = gridSizer.height
        val split = height / puzzleSize

        var row = kotlin.math.floor(y.toDouble() / split).toInt()
        var col = kotlin.math.floor(x.toDouble() / split).toInt()
        if (row < 0) row = 0
        if (col < 0) col = 0
        row = min(row, puzzleSize - 1)
        col = min(col, puzzleSize - 1)
        return row * puzzleSize + col
    }

    fun isValidWord(startX: Float, startY: Float, endX: Float, endY: Float) : Boolean{
        val ind1 = getGridCellIndex(startX, startY)
        val row1 = (ind1 / puzzleSize)
        val col1 = ind1 % puzzleSize

        val ind2 = getGridCellIndex(endX, endY)
        val row2 = (ind2 / puzzleSize)
        val col2 = ind2 % puzzleSize

        for(i in wordList.indices){
            val word = wordList[i]
            if(word == null) continue
            if(word.getStartPt()[0] == row1 && word.getStartPt()[1] == col1 && word.getEndPt()[0] == row2 && word.getEndPt()[1] == col2
                || word.getEndPt()[0] == row1 && word.getEndPt()[1] == col1 && word.getStartPt()[0] == row2 && word.getStartPt()[1] == col2){

                foundWord(i)

                //If all words discovered, win level
                if(wordCounter == wordList.size){
                    gainFish()
                }

                gainBread()
                return true
            }
        }
        return false
    }

    fun foundWord(index: Int){
        val arr = posInWord(wordList[index]!!)
        for(ar in arr){
            breadHighlights[ar[0]][ar[1]] = false
            letters[ar[0]*puzzleSize + ar[1]]!!.setBackgroundResource(0)

        }
        wordList[index] = null
        //Update wordCounter and cross off word from word bank
        wordCounter++
        if(isAudioPuzzle){
            val id = 2001
            val wordsLeft = findViewById<TextView>(id)
            wordsLeft.text = "$wordCounter / " + wordList.size
        } else {
            val wordText = findViewById<TextView>(index + 2000)
            wordText.paintFlags = wordText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        if(sp.getBoolean(getString(R.string.SOUNDS),false)){
            soundEffect = createMedia("word_reward")
            soundEffect.start()
        }


        //If all words discovered, win puzzle
        if(wordCounter == wordList.size){
            winPuzzle()
        }
    }


    //Subtracts 1 from the fish number when tapped
    fun useFish(view: View) {
        val fishString = fishScoreNumber.text.toString()
        var fishInt = fishString.toInt()

        if (fishInt > 0){
            fishInt--

            val edit = sp.edit()
            edit.putInt(getString(R.string.fish_key),fishInt)
            edit.commit()

            fishScoreNumber.text = fishInt.toString()
            // Reveal a random entire word
            val numWords = wordList.size - wordCounter
            var ct = 0
            for(i in wordList.indices){
                val word = wordList[i]
                if(word == null) continue
                else{
                    ct++
                    if(Math.random() < ct.toDouble() / numWords) {
                        val startLetter = letters[word.getStartPt()[0]*puzzleSize + word.getStartPt()[1]]
                        val endLetter = letters[word.getEndPt()[0]*puzzleSize + word.getEndPt()[1]]
                        canvas.highlightLetters(startLetter, endLetter)
                        //highlight word
                        foundWord(i)
                        break
                    }
                }
            }
        }
    }

    //Subtracts 1 from the bread number when tapped
    fun useBread(view: View) {
        val breadString = breadScoreNumber.text.toString()
        var breadInt = breadString.toInt()

        if (breadInt > 0) {
            breadInt--
            val edit = sp.edit()
            edit.putInt(getString(R.string.bread_key),breadInt)
            edit.commit()
            breadScoreNumber.text = breadInt.toString()

            var randRow = (Math.random()*breadHighlights.size).toInt()
            var randCol = (Math.random() * breadHighlights.size).toInt()
            val sr = randRow
            val sc = randCol
            while(breadHighlights[randRow][randCol] || !isWordPos( intArrayOf(randRow,randCol) ) ) {
                randCol++
                if(randCol % puzzleSize == 0) {
                    randCol = 0
                    randRow = (randRow + 1) % puzzleSize
                }
                if(randRow == sr && randCol ==sc){
                    break
                }
                //there WILL be a spot left to highlight, otherwise a word got finished and not removed
            }
            breadHighlights[randRow][randCol] = true
            breadHighlights[randRow][randCol] = true
            letters[(randRow * puzzleSize + randCol)]!!.setBackgroundResource(R.color.colorPrimary)
            removeBreadWords()

            /*
                Don't highlight = bold/background letters.
                ..pick a random pos in the board, cycle until find a pos in a word
             */
        }
    }

    fun removeBreadWords(){
        for(i in wordList.indices){
            val word = wordList[i]
            if(word==null)continue
            val pointList = posInWord(word)
            var wordAllHighlighted = true
            for(arr in pointList){
                if(!breadHighlights[arr[0]][arr[1]]){
                    wordAllHighlighted = false
                }
            }
            if(wordAllHighlighted){
                foundWord(i)
                val startLetter = letters[word.getStartPt()[0]*puzzleSize + word.getStartPt()[1]]
                val endLetter = letters[word.getEndPt()[0]*puzzleSize + word.getEndPt()[1]]
                canvas.highlightLetters(startLetter, endLetter)

                for(arr in pointList){
                    breadHighlights[arr[0]][arr[1]] = false
                    letters[arr[0]*puzzleSize + arr[1]]!!.setBackgroundResource(0)
                }

            }

        }
    }

    fun isWordPos(coords : IntArray) : Boolean{
        for(word in wordList){
            if(word == null) continue
            for(intarr in posInWord(word)){
                if(coords.contentEquals(intarr)){
                    return true
                }
            }
        }
        return false
    }

    fun posInWord(word: Word) : Array<IntArray> {
        val sr = word.getStartPt()[0]
        val sc = word.getStartPt()[1]
        val er = word.getEndPt()[0]
        val ec = word.getEndPt()[1]
        val len =
            if(sr == er){
                abs(sc - ec) + 1
            } else {
                abs(sr- er) +1
            }
        val toReturn = Array(len){intArrayOf(0,0)}
        toReturn[0]=word.getStartPt()
        for(i in 1 until len) {
            toReturn[i] = intArrayOf(((er-sr) * i / (len-1) + sr), ((ec-sc) * i / (len-1) + sc))
        }
        return toReturn
    }

    fun gainBoat(){
        val boatString = boatScoreNumber.text.toString()
        var boatInt = boatString.toInt()

        boatInt++
        boatScoreNumber.text = boatInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.boat_key),boatInt)
        edit.commit()
    }

    fun gainFish(){
        val fishString = fishScoreNumber.text.toString()
        var fishInt = fishString.toInt()

        fishInt++
        fishScoreNumber.text = fishInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.fish_key),fishInt)
        edit.commit()
    }

    fun gainBread(){
        val breadString = breadScoreNumber.text.toString()
        var breadInt = breadString.toInt()

        breadInt++
        breadScoreNumber.text = breadInt.toString()
        val edit = sp.edit()
        edit.putInt(getString(R.string.bread_key),breadInt)
        edit.commit()
    }

    fun winPuzzle(){
        val levelComplete = db.markPuzzleCompleted(puzzleEngine.puzzle.id)
        if(levelComplete){
            gainBoat()
            puzzleEngine.puzzle.level_id
        }
        if(sp.getBoolean(getString(R.string.SOUNDS),false)){
            soundEffect = createMedia("complete_puzzle")
            soundEffect.start()
        }
        showPopUp(this, false, "", true, puzzleEngine.puzzle.id)
    }

    //Creates and prepares media to be played
    //Throws FormatException if file is not an mp3
    @Throws(FormatException::class)
    private fun createMedia(mp3: String): MediaPlayer {
        return if (mp3.isNotEmpty()) {
            val id = resources.getIdentifier(mp3, "raw", packageName)
            MediaPlayer.create(this, id)
        } else {
            throw FormatException()
        }
    }

    override fun onStop() {
        if(isAudioPuzzle) {
            media.release()
        }
        super.onStop()
    }
}