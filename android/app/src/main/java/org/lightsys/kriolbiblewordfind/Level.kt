package org.lightsys.kriolbiblewordfind

import android.content.Context

class Level(var id:Int = -1,var title:String = "",var completed:Boolean = false,var picture:String = "",var word_file:String = ""){

    fun getWordList(context: Context): ArrayList<String> {
        val resID = context.resources.getIdentifier(word_file, "raw", context.getString(R.string.package_name))
        val wordInputStream = context.resources.openRawResource(resID)
        val words:ArrayList<String> = ArrayList()
        wordInputStream.bufferedReader().useLines { lines -> lines.forEach{
                words.add(it.trim())
            }
        }
        return words
    }
    fun getImageFileId(context: Context): Int {
        return context.resources.getIdentifier(picture, "raw", context.getString(R.string.package_name))
    }
}