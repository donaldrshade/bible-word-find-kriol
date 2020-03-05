package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.content.res.Resources

class Puzzle(var id:Int = -1,var size:Int = -1,var numOfWords:Int = -1,var completed:Boolean=false,var audioFile:String="",var level_id:Int=-1,var min_num_letters:Int=-1,var max_num_letters:Int=-1 ) {
    fun getWordList(context: Context): ArrayList<String> {
        val db = Database(context)
        val level = db.getLevel(level_id)
        return level.getWordList(context)
    }
    fun getAudioFileId(context: Context): Int {
        return context.resources.getIdentifier(audioFile, "raw", context.getString(R.string.package_name));
    }

    fun getWordSizes(): IntArray{
        return intArrayOf(min_num_letters, max_num_letters)
    }
}