package org.lightsys.kriolbiblewordfind

import android.content.Context
import java.util.ArrayList
import java.util.Arrays

class PuzzleEngine(pNum: Int, val context: Context) {
    private val display: Array<CharArray>
    private val words: Array<Word?>
    var puzzle = Puzzle()
    var level = Level()
    val grid: Array<CharArray>
        get() = display.clone()


    init {
        var db = Database(context)
        puzzle = db.getPuzzle(pNum)
        level = db.getLevel(puzzle.level_id)
        //get

        display = Array(puzzle.size) { CharArray(puzzle.size) }
        words = arrayOfNulls(puzzle.numOfWords)

        val pool = getWordList()

        var wordSizes = getWordSizes()
        if (wordSizes.size == 1) {
            wordSizes = intArrayOf(wordSizes[0], wordSizes[0])
        }
        val options = ArrayList<String>()
        for (i in pool.indices) {
            if (pool[i].length >= wordSizes[0] && pool[i].length <= wordSizes[1]) {
                options.add(pool[i])
            }
        }

        for (i in words.indices) {
            val rand = (Math.random() * options.size).toInt()
            words[i] = Word(options.removeAt(rand))
        }


        fillDisplay(false)

        Arrays.sort(words)
    }


    private fun getSize(): Int {
        return puzzle.size
    }

    private fun getWordCt(): Int {

        return puzzle.numOfWords
    }

    private fun getWordList(): Array<String> { //array needs to be length-sorted (smallest first)
        val string = level.word_file

        val temp = Array<String>(4) {""}

//        context.resources.openRawResource(R.raw.)
//        return Tester.getWordList()
        return temp
    }

    private fun getWordSizes(): IntArray {
        var temp = IntArray(2)
        temp[0] = puzzle.min_num_letters
        temp[1] = puzzle.max_num_letters
        return temp


    }

    private fun fillDisplay(overlap: Boolean) {
        for (r in words.indices) {
            val s = words[r]!!.word
            for (c in 0 until s.length) {
                display[r][c] = Character.toUpperCase(s[c])
            }
            for (c in s.length until display.size) {
                display[r][c] = randLetter()
            }
            words[r]!!.setStartPt(r, 0)
            words[r]!!.setEndPt(r, s.length)
        }
        for (r in words.size until display.size) {
            for (c in display.indices) {
                display[r][c] = randLetter()
            }
        }


    }

    private fun randLetter(): Char {
        return (Math.random() * ('Z' - 'A') + 'A'.toDouble()).toInt().toChar()
    }

    fun getWords(): Array<Word?> {
        return words.clone()
    }
}

class Word(val word: String) : Comparable<Word> {
    private val startPt = intArrayOf(0, 0)
    private val endPt = intArrayOf(0, 0)

    override fun compareTo(other: Word): Int {
        return this.word.compareTo(other.word, ignoreCase = true)
    }

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
}
