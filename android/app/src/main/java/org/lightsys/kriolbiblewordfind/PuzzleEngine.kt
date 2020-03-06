import android.content.Context
import org.lightsys.kriolbiblewordfind.Puzzle
import java.util.*

class PuzzleEngine(var puzzle: Puzzle, var context: Context) {
    private var display: Array<CharArray>
    private val words: Array<Word?>

    private fun fillDisplayBasic() {
        var numWordsLeft = words.size
        var numLinesLeft = display.size

        //shuffle words
        for (i in words.indices) {
            val swap = (Math.random() * (words.size - i)).toInt()
            val sv = words[i]
            words[i] = words[swap]
            words[swap] = sv
        }
        for (r in display.indices) {
            if (numWordsLeft != numLinesLeft && (numWordsLeft == 0 || Math.random() > numWordsLeft / numLinesLeft.toDouble())) {
                //skip this line
                //System.err.println("skip line");
                for (c in display.indices) {
                    display[r][c] = randLetter()
                }
                numLinesLeft--
            } else {
                val s = words[words.size - numWordsLeft]!!.word
                val forwards = Math.random() < 0.5
                //System.out.println(s + " " + forwards + " r: " + r);
                val charSkip = (Math.random() * (display.size - s.length)).toInt()
                var c = 0
                c = 0
                while (c < charSkip) {
                    display[r][if (forwards) c else display.size - 1 - c] = randLetter()
                    c++
                }
                while (c < charSkip + s.length) {
                    display[r][if (forwards) c else display.size - 1 - c] =
                        Character.toUpperCase(s[c - charSkip])
                    c++
                }
                while (c < display.size) {
                    display[r][if (forwards) c else display.size - 1 - c] = randLetter()
                    c++
                }
                if (forwards) {
                    words[words.size - numWordsLeft]!!.setStartPt(r, charSkip)
                    words[words.size - numWordsLeft]!!.setEndPt(r, charSkip + s.length - 1)
                } else {
                    words[words.size - numWordsLeft]!!.setStartPt(r, display.size - 1 - charSkip)
                    words[words.size - numWordsLeft]!!.setEndPt(r, display.size - charSkip - s.length)
                }
                numLinesLeft--
                numWordsLeft--
            }
        }
    }

    private fun fillDisplay(overlap: Boolean) {
        if (!overlap) {
            fillDisplayBasic()
            return
        }


        //pre - words list is sorted by length
        val tempGrids =
            Stack<Array<CharArray>>()
        //- this is for storing the grids as recursion goes on
        val rc = Array(words.size) { IntArray(2) }
        //- this is for storing the positions of each word so that when we restart, we don't
        //hit the same position over and over again
        for (arr in rc) {
            for (i in arr.indices) {
                arr[i] = -1
            }
        }
        val checkedSpots =
            ArrayList<Array<BooleanArray>>()
        //this will be empty if this word's position has not been tried anywhere yet
        var RESET_CT = 0
        //- this is for breaking the loop if recursion goes too long
        var i = 0
        while (i < words.size) {
            if (i < 0) {
                fillDisplayBasic()
                return
            }
            //- this loop indicates which word in the list we are trying to stick in the grid. We
            //-	will backtrack in this loop occasionally with an i -= 2 (followed by the i++ is i--)
            tempGrids.add(display.clone())
            //save a copy of the board before we change it
            val word = words[i]!!.word.toCharArray()
            //longest word is at front
            var s_r: Int
            var s_c: Int
            if (checkedSpots.size == i) {
                //nothing has been tried for this word and setup yet
                s_r = (Math.random() * display.size).toInt()
                s_c = (Math.random() * display.size).toInt()
                checkedSpots.add(
                    Array(
                        display.size
                    ) { BooleanArray(display.size) }
                )
            } else {
                s_r = rc[i][0] + 1
                s_c = rc[i][1]
                if (s_r == display.size) { //correction for incremented loaded values
                    s_c %= display.size
                    s_r = (s_r + 1) % display.size
                }
            }
            //loading will skip other dirs on a space
            var r = s_r
            var c = s_c
            var d: Int
            var worked = false
            while (!checkedSpots[i][r][c]) {
                checkedSpots[i][r][c] = true

                //test display[r][c] in all 8 dirs as possible location
                val dir = (Math.random() * 8).toInt()
                d = dir
                while (d < dir + 8) {
                    worked = fitWord(r, c, d, word)
                    if (worked) {
                        writeWord(r, c, d, word)
                        words[i]!!.setStartPt(r, c)
                        when (d % 8) {
                            0 -> words[i]!!.setEndPt(r - (word.size - 1), c)
                            1 -> words[i]!!.setEndPt(r - (word.size - 1), c + (word.size - 1))
                            2 -> words[i]!!.setEndPt(r, c + (word.size - 1))
                            3 -> words[i]!!.setEndPt(r + (word.size - 1), c + (word.size - 1))
                            4 -> words[i]!!.setEndPt(r + (word.size - 1), c)
                            5 -> words[i]!!.setEndPt(r + (word.size - 1), c - (word.size - 1))
                            6 -> words[i]!!.setEndPt(r, c - (word.size - 1))
                            7 -> words[i]!!.setEndPt(r - (word.size - 1), c - (word.size - 1))
                        }
                        break
                    }
                    d++
                }
                if (worked) {
                    //board should already be filled at r,c,d; and word's info filled
                    rc[i][0] = r
                    rc[i][1] = c
                    break
                }
                c = (c + 1) % display.size
                r += if (c == 0) 1 else 0
                r %= display.size
            }
            if (!worked) {
                RESET_CT++
                rc[i][0] = -1
                rc[i][1] =
                    -1 //erase any rc for this word, for last word is saved as restart pt
                i -= 2 //will be incremented due to i++ of for loop
                //erases the change before this "change"
                display = tempGrids.pop()
                if (!tempGrids.isEmpty()) {
                    display =
                        tempGrids.pop() //sets display to the right version (will get pushed back on)
                }
                checkedSpots.removeAt(checkedSpots.size - 1) //all true because !worked
            }
            i++
        }
        //board got generated
        for (arr in display) {
            for (i in arr.indices) {
                if (arr[i] == '\u0000') {
                    arr[i] = randLetter()
                }
            }
        }
    }

    private fun fitWord(r: Int, c: Int, d: Int, word: CharArray): Boolean {
        return placeWord(r, c, d, word, false)
    }

    private fun writeWord(r: Int, c: Int, d: Int, word: CharArray) {
        placeWord(r, c, d, word, true)
    }

    private fun placeWord(
        r: Int,
        c: Int,
        d: Int,
        word: CharArray,
        write: Boolean
    ): Boolean {
        for (delta in word.indices) {
            when (d % 8) {
                0 -> if (!fits(r - delta, c, word[delta], write)) {
                    return false
                }
                1 -> if (!fits(r - delta, c + delta, word[delta], write)) {
                    return false
                }
                2 -> if (!fits(r, c + delta, word[delta], write)) {
                    return false
                }
                3 -> if (!fits(r + delta, c + delta, word[delta], write)) {
                    return false
                }
                4 -> if (!fits(r + delta, c, word[delta], write)) {
                    return false
                }
                5 -> if (!fits(r + delta, c - delta, word[delta], write)) {
                    return false
                }
                6 -> if (!fits(r, c - delta, word[delta], write)) {
                    return false
                }
                7 -> if (!fits(r - delta, c - delta, word[delta], write)) {
                    return false
                }
            }
        }
        return true
    }

    private fun fits(r: Int, c: Int, ch: Char, write: Boolean): Boolean {
        if (r < 0 || c < 0 || r >= display.size || c >= display.size) {
            return false
        }
        if (display[r][c] == '\u0000' || display[r][c] == ch) {
            if (write) {
                display[r][c] = Character.toUpperCase(ch)
            }
            return true
        }
        return false
    }

    private fun randLetter(): Char {
        return (Math.random() * ('Z' - 'A') + 'A'.toDouble()).toInt().toChar()
    }

    val grid: Array<CharArray>
        get() = display.clone()

    fun getWords(): Array<Word?> {
        return words.clone()
    }

    init {
        //get
        if(puzzle.id == -1){ //tutorial puzzle
            display = Array(4) {CharArray(4)}
            var i = 0;
            for(c in "EDAM"){
                display[0][i] = c;
                i++
            }
            i = 0;
            for(c in "NAGO"){
                display[1][i] = c;
                i++;
            }
            i = 0;
            for(c in "IGTA"){
                display[2][i] = c;
                i++;
            }
            i = 0;
            for(c in "FABB"){
                display[3][i] = c;
                i++;
            }
            words = arrayOf(
                    new Word("EDAM"),
                    new Word("DAGA"),
                    new Word("MOAB")
            )
            words[0].setStartPt(0,0);
            words[0].setEndPt(0,3);
            words[1].setStartPt(0,1);
            words[1].setEndPt(3,1);
            words[2].setStartPt(0,3);
            words[2].setEndPt(3,3);
        } else {
            val s = puzzle.size
            val wCt = puzzle.numOfWords
            display = Array(s) { CharArray(s) }
            words = arrayOfNulls(wCt)
            val pool = puzzle.getWordList(context)
            var wordSizes = puzzle.getWordSizes()
            val options = ArrayList<String>()
            for (i in pool.indices) {
                if (pool[i].length >= wordSizes[0] && pool[i].length <= wordSizes[1]) {
                    options.add(pool[i])
                }
            }
            for (i in words.indices) {
                val rand = (Math.random() * options.size).toInt()
                var insert: Word? = Word(options.removeAt(rand))
                var x = 0
                x = 0
                while (x < i) {
                    if (words[x]!!.word.length < insert!!.word.length) {
                        val sv = words[x]
                        words[x] = insert
                        insert = sv
                    }
                    x++
                }
                words[i] = insert //fills words up by decreasing length
            }
            fillDisplay(true)
            Arrays.sort(words)
        }
    }
}

class Word(val word: String) : Comparable<Word?> {
    private val startPt = intArrayOf(0, 0)
    private val endPt = intArrayOf(0, 0)

    fun getStartPt(): IntArray {
        return startPt.clone()
    }

    fun setStartPt(a: Int, b: Int) {
        if (a < 0 || b < 0) return
        startPt[0] = a
        startPt[1] = b
    }

    fun getEndPt(): IntArray {
        return endPt.clone()
    }

    fun setEndPt(a: Int, b: Int) {
        if (a < 0 || b < 0) return
        endPt[0] = a
        endPt[1] = b
    }

    override fun compareTo(other: Word?): Int {
        return word.compareTo(other!!.word, ignoreCase = true)
    }
}