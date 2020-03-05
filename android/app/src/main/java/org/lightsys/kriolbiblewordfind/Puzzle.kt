package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.content.res.Resources

class Puzzle(var id:Int = -1,var size:Int = -1,var numOfWords:Int = -1,var completed:Boolean=false,var audioFile:String="",var level_id:Int=-1,var min_num_letters:Int=-1,var max_num_letters:Int=-1 ) {
    fun getWordList(packageName: String, context: Context): ArrayList<String> {
        val db = Database(context)
        val level = db.getLevel(level_id)
        return level.getWordList(packageName, context)
    }
    fun getAudioFileId(packageName: String, context: Context): Int {
        return context.resources.getIdentifier(audioFile, "drawable", packageName);
    }
}