package org.lightsys.kriolbiblewordfind

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class Level(var id:Int = -1,var title:String = "",var completed:Boolean = false,var picture:String = "",var word_file:String = ""){
    fun getWordList(packageName: String, context: Context): ArrayList<String> {
        val resID = context.resources.getIdentifier(word_file, "drawable", packageName);
        val wordInputStream = context.resources.openRawResource(resID);
        var words:ArrayList<String> = ArrayList()
        wordInputStream.bufferedReader().useLines { lines -> lines.forEach{
                words.add(it.trim())
            }
        }
        return words
    }

}