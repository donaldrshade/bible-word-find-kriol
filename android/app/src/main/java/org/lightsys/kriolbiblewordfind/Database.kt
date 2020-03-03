package org.lightsys.kriolbiblewordfind

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

/**
 * This class is where we store data over app closes.
 * The functions interact with the database so that we don't have to instantiate on our own.
 * Just get a new connection.
 */

class Database(context: Context,appName:String) :
    SQLiteOpenHelper(context, "$appName.db", null, versionNumber){



    override fun onCreate(db: SQLiteDatabase) {
        //This function only runs the first time the app is run. See comment above.
        createTables(db)
        fillTables(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //new code will go here for the upgrade
        //Use the Alter Table table_name ADD new_column_name column_default_data
        //find out more at https://www.techonthenet.com/sqlite/tables/alter_table.php
        when (newVersion) {

        }
    }

    /**
     * @param db
     * This function is run with the start and only the first time the app is run.
     */
    private fun createTables(db: SQLiteDatabase) {
        val lessonTableCreate = String.format(
            "create table %s ( %s INTEGER PRIMARY KEY AUTO_INCREMENT,%s TEXT,%s INTEGER,%s TEXT,%s TEXT)",
            LEVEL_TABLE_NAME,
            LEVEL_COL_1,
            LEVEL_COL_2,
            LEVEL_COL_3,
            LEVEL_COL_4,
            LEVEL_COL_5
        )
        db.execSQL(lessonTableCreate)
        val puzzleTableCreate = String.format(
            "create table %s ( %s INTEGER PRIMARY KEY AUTO_INCREMENT,%s INTEGER,%s INTEGER,%s INTEGER,%s TEXT,%s INTEGER,FOREIGN KEY(%s) REFERENCES %s(%s))",
            PUZZLE_TABLE_NAME,
            PUZZLE_COL_1,
            PUZZLE_COL_2,
            PUZZLE_COL_3,
            PUZZLE_COL_4,
            PUZZLE_COL_5,
            PUZZLE_COL_6,
            PUZZLE_COL_6,
            LEVEL_TABLE_NAME,
            LEVEL_COL_1
        )
        db.execSQL(puzzleTableCreate)
    }
    private fun fillTables(db: SQLiteDatabase) {
        //Insert into the Level Table
        val levelString = "INSERT INTO $LEVEL_TABLE_NAME ( $LEVEL_COL_1, $LEVEL_COL_2, $LEVEL_COL_3,$LEVEL_COL_4,$LEVEL_COL_5) VALUES"

        db.execSQL(levelString+"(1,Jisas bin stapam win en weib (Mak 4:35-41), 0, "+"\"Mak4.jpg\" , "+"\"Mark 4 35-41 A.txt\")")
        db.execSQL(levelString+"(2,Jisas bin weikimap Lesaras brom dedbala (Jon 11:38-44), 0, "+"\"Lazarus.jpg\" , "+"\"John 11 38-44 A.txt\")")
        db.execSQL(levelString+"(3,Jisas bin bon (Methyu 1:18-25), 0, "+"\"Methyu1.jpg\" , "+"\"Matthew 1 18-25 A.txt\")")
        db.execSQL(levelString+"(4,Jona bin jingat langa God (Jona 2:1-10), 0, "+"\"Jona.jpg\" , "+"\"Jonah 2 1-10 A.txt\")")
        db.execSQL(levelString+"(5,Pida bin meigim wan leimbala men gudwan (Eks 3:1-10), 0, "+"\"Eks3.jpg\" , "+"\"Acts 3 1-10 A.txt\")")
        db.execSQL(levelString+"(6,Josef en im braja olabat (Jenasis 37:1-11), 0, "+"\"Jenasis37.jpg\" , "+"\"Genesis 37 1-11 A.txt\")")
        db.execSQL(levelString+"(7,Thribala fishamen (Lul 5:1-11), 0, "+"\"Luk5.jpg\" , "+"\"Luke 5 1-11 A.txt\")")
        db.execSQL(levelString+"(8,Detlot speshalwan klos blanga ola Kristjan pipul (Ifishans 6:10-20), 0, "+"\"Ifishans.jpg\" , "+"\"Ephesians 6 10-20 A.txt\")")
        db.execSQL(levelString+"(9,Jisas bin stapam win en weib (Mak 4:35-41), 0, "+"\"Mak4.jpg\" , "+"\"Mark 4 35-41 A.txt\")")
        db.execSQL(levelString+"(10,Jona bin jingat langa God (Jona 2:1-10), 0, "+"\"Jona.jpg\" , "+"\"Jonah 2 1-10 A.txt\")")
        db.execSQL(levelString+"(11,Pida bin meigim wan leimbala men gudwan (Eks 3:1-10), 0, "+"\"Eks3.jpg\" , "+"\"Acts 3 1-10 A.txt\")")
        db.execSQL(levelString+"(12,Detlot speshalwan klos blanga ola Kristjan pipul (Ifishans 6:10-20), 0, "+"\"Ifishans.jpg\" , "+"\"Ephesians 6 10-20 A.txt\")")
        db.execSQL(levelString+"(13,Raida langa waitwan hos (Rebaleishan 19:11-21), 0, "+"\"Rebaleishan.jpg\" , "+"\"Revelation 19 11-21 A.txt\")")
        db.execSQL(levelString+"(14,Detlot 7 san blanga Seba (Eks 19:11-22), 0, "+"\"Ephesus.jpg\" , "+"\"Acts 19 11-22 A.txt\")")
        db.execSQL(levelString+"(15,Pol bin prei blanga olabat (Kaloshans 1:3-14), 0, "+"\"Kaloshans.jpg\" , "+"\"Collossians 1 3-14 A.txt\")")
        db.execSQL(levelString+"(16,Wan lowamen bin wandi trikim Jisas (Luk 10:25-37), 0, "+"\"Luk10.jpg\" , "+"\"Luke 10 25-37 A.txt\")")
        db.execSQL(levelString+"(17,Seitin bin temtimbat Jisas (Luk 4:1-13), 0, "+"\"Temptation.jpg\" , "+"\"Luke 4 1-13 A.txt\")")
        db.execSQL(levelString+"(18,Jisas en im braja olabat (Hibrus 2:5-18), 0, "+"\"FesPida3.jpg\" , "+"\"hebrews 2 5-18 A.txt\")")
        db.execSQL(levelString+"(19,Teiknodis langa det trubalawan wed (Kaloshans 2:6-19), 0, "+"\"FesPida3.jpg\" , "+"\"Collosians 2 6-19 A.txt\")")
        db.execSQL(levelString+"(20,Joshuwa bin bidim ola Emarait pipul (Joshuwa 10:1-15), 0, "+"\"Joshuwa.jpg\" , "+"\"Joshua 10 1-15 A.txt\")")
        db.execSQL(levelString+"(21,Jisas bin fidim 5,000 men (Mak 6:30-44), 0, "+"\"Mak6.jpg\" , "+"\"Mark 6 30-44 A.txt\")")
        db.execSQL(levelString+"(22,God na meigim wi laigim gija (Fes Jon 4:7-21), 0, "+"\"Lovelight.jpg\" , "+"\"1 John 4 7-21 A.txt\")")
        db.execSQL(levelString+"(23,Kristjan pipul garra abum trabul blanga gudbala ting  (Fes Pida 3:8-22), 0, "+"\"FesPida3.jpg\" , "+"\"1 Peter 3 8-22 A.txt\")")
        db.execSQL(levelString+"(24,Seitin bin temtimbat Jisas (Luk 4:1-13), 0, "+"\"Temptation.jpg\" , "+"\"Luke 4 1-13 A.txt\")")
        db.execSQL(levelString+"(25,Jisas bin fidim 5,000 men (Mak 6:30-44), 0, "+"\"Mak6.jpg\" , "+"\"Mark 6 30-44 A.txt\")")
        db.execSQL(levelString+"(26,Nogudbala spirit en bigibigi (Mak 5:1-20), 0, "+"\"Mak5.jpg\" , "+"\"Mark 5 1-20 A.txt\")")
        db.execSQL(levelString+"(27,Mosis en ola pipul bin preisim God (Eksadas 15:1-21), 0, "+"\"Eksadas15.jpg\" , "+"\"Exodus 15 1-21 A.txt\")")
        db.execSQL(levelString+"(28,Nikadimas bin kaman langa Jisas (Jon 3:1-21), 0, "+"\"Nicodemus.jpg\" , "+"\"John 3 1-21 A.txt\")")
        db.execSQL(levelString+"(29,God bin kilim ola feswan san (Eksadas 12:21-42), 0, "+"\"Eksadas12.jpg\" , "+"\"Exodus 12 21-42 A.txt\")")
        db.execSQL(levelString+"(30,Dubala bin dagat daga (Jenasis 3:1-24), 0, "+"\"Jenasis3.jpg\" , "+"\"Gen 3 1-24 A.txt\")")
        db.execSQL(levelString+"(31,Denyul langa det hol weya ola laiyan oldei jidan (Denyul 6:1-28), 0, "+"\" Denyul6.jpg\" , "+"\"Daniel 6 1-28 A.txt\")")
        db.execSQL(levelString+"(32,God bin meigim ebrijing (Jenasis 1:1-2:3), 0, "+"\"Jenasis1.jpg\" , "+"\"Genesis 1 all & 2 1-4 A.txt\")")
        db.execSQL(levelString+"(33,Ilaija en detlot mesinja blanga Beil (Fes Kings 18:1-40), 0, "+"\"Elijah.jpg\" , "+"\"1 Kings 18 1-40 A.txt\")")
        db.execSQL(levelString+"(34,Deibid bin bidim Galaiyath (Fes Semyul 17:1-58), 0, "+"\"David.jpg\" , "+"\"1 Samuel 17 1-58 A.txt\")")
        db.execSQL(levelString+"(35,Nogudbala spirit en bigibigi (Mak 5:1-20), 0, "+"\"Mak5.jpg\" , "+"\"Mark 5 1-20 A.txt\")")
        db.execSQL(levelString+"(36,God bin meigim ebrijing (Jenasis 1:1-2:3), 0, "+"\"Jenasis1.jpg\" , "+"\"Genesis 1 all & 2 1-4 A.txt\")")
        db.execSQL(levelString+"(37,Jisas bin stapam win en weib (Mak 4:35-41), 0, "+"\"Mak4.jpg\" , "+"\"Mark 4 35-41 A.txt\")")
        db.execSQL(levelString+"(38,Jona bin jingat langa God (Jona 2:1-10), 0, "+"\"Jona.jpg\" , "+"\"Jonah 2 1-10 A.txt\")")
        db.execSQL(levelString+"(39,Pida bin meigim wan leimbala men gudwan (Eks 3:1-10), 0, "+"\"Eks3.jpg\" , "+"\"Acts 3 1-10 A.txt\")")
        db.execSQL(levelString+"(40,Detlot speshalwan klos blanga ola Kristjan pipul (Ifishans 6:10-20), 0, "+"\"Ifishans.jpg\" , "+"\"Ephesians 6 10-20 A.txt\")")
        db.execSQL(levelString+"(41,Seitin bin temtimbat Jisas (Luk 4:1-13), 0, "+"\"Temptation.jpg\" , "+"\"Luke 4 1-13 A.txt\")")
        db.execSQL(levelString+"(42,Jisas bin fidim 5,000 men (Mak 6:30-44), 0, "+"\"Mak6.jpg\" , "+"\"Mark 6 30-44 A.txt\")")
        db.execSQL(levelString+"(43,Nogudbala spirit en bigibigi (Mak 5:1-20), 0, "+"\"Mak5.jpg\" , "+"\"Mark 5 1-20 A.txt\")")
        db.execSQL(levelString+"(44,God bin meigim ebrijing (Jenasis 1:1-2:3), 0, "+"\"Jenasis1.jpg\" , "+"\"Genesis 1 all & 2 1-4 A.txt\")")

        //Insert Into the Puzzle Tables
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(1,3, 2,0,"+"\"NULL\""+",1)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(2,4, 3,0,"+"\"NULL\""+",1)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(3,5, 4,0,"+"\"NULL\""+",1)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(4,5, 5,0,"+"\"NULL\""+",1)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(5,3, 2,0,"+"\"NULL\""+",2)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(6,4, 3,0,"+"\"NULL\""+",2)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(7,5, 4,0,"+"\"NULL\""+",2)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(8,5, 5,0,"+"\"NULL\""+",2)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(9,3, 2,0,"+"\"NULL\""+",3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(10,4, 3,0,"+"\"NULL\""+",3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(11,5, 4,0,"+"\"NULL\""+",3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(12,5, 5,0,"+"\"NULL\""+",3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(13,3, 2,0,"+"\"NULL\""+",4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(14,4, 3,0,"+"\"NULL\""+",4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(15,5, 4,0,"+"\"NULL\""+",4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(16,5, 5,0,"+"\"NULL\""+",4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(17,3, 2,0,"+"\"NULL\""+",5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(18,4, 3,0,"+"\"NULL\""+",5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(19,5, 4,0,"+"\"NULL\""+",5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(20,5, 5,0,"+"\"NULL\""+",5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(21,3, 2,0,"+"\"NULL\""+",6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(22,4, 3,0,"+"\"NULL\""+",6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(23,5, 4,0,"+"\"NULL\""+",6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(24,5, 5,0,"+"\"NULL\""+",6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(25,3, 2,0,"+"\"NULL\""+",7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(26,4, 3,0,"+"\"NULL\""+",7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(27,5, 4,0,"+"\"NULL\""+",7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(28,5, 5,0,"+"\"NULL\""+",7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(29,3, 2,0,"+"\"NULL\""+",8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(30,4, 3,0,"+"\"NULL\""+",8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(31,5, 4,0,"+"\"NULL\""+",8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(32,5, 5,0,"+"\"NULL\""+",8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(33,1, 12,0,"+"\"Kriol MRK_4 35-41.mp3\""+",9)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(34,1, 12,0,"+"\"Kriol JONAH 2.mp3\""+",10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(35,1, 12,0,"+"\"Kriol ACT_3 1-10.mp3\""+",11)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(36,1, 12,0,"+"\"Kriol EPH_6 10-20.mp3\""+",12)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(37,5, 4,0,"+"\"NULL\""+",13)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(38,6, 5,0,"+"\"NULL\""+",13)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(39,7, 6,0,"+"\"NULL\""+",13)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(40,7, 7,0,"+"\"NULL\""+",13)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(41,5, 4,0,"+"\"NULL\""+",14)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(42,6, 5,0,"+"\"NULL\""+",14)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(43,7, 6,0,"+"\"NULL\""+",14)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(44,7, 7,0,"+"\"NULL\""+",14)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(45,5, 4,0,"+"\"NULL\""+",15)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(46,6, 5,0,"+"\"NULL\""+",15)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(47,7, 6,0,"+"\"NULL\""+",15)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(48,7, 7,0,"+"\"NULL\""+",15)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(49,5, 4,0,"+"\"NULL\""+",16)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(50,6, 5,0,"+"\"NULL\""+",16)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(51,7, 6,0,"+"\"NULL\""+",16)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(52,7, 7,0,"+"\"NULL\""+",16)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(53,5, 4,0,"+"\"NULL\""+",17)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(54,6, 5,0,"+"\"NULL\""+",17)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(55,7, 6,0,"+"\"NULL\""+",17)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(56,7, 7,0,"+"\"NULL\""+",17)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(57,5, 4,0,"+"\"NULL\""+",18)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(58,6, 5,0,"+"\"NULL\""+",18)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(59,7, 6,0,"+"\"NULL\""+",18)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(60,7, 7,0,"+"\"NULL\""+",18)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(61,5, 4,0,"+"\"NULL\""+",19)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(62,6, 5,0,"+"\"NULL\""+",19)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(63,7, 6,0,"+"\"NULL\""+",19)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(64,7, 7,0,"+"\"NULL\""+",19)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(65,5, 4,0,"+"\"NULL\""+",20)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(66,6, 5,0,"+"\"NULL\""+",20)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(67,7, 6,0,"+"\"NULL\""+",20)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(68,7, 7,0,"+"\"NULL\""+",20)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(69,5, 4,0,"+"\"NULL\""+",21)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(70,6, 5,0,"+"\"NULL\""+",21)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(71,7, 6,0,"+"\"NULL\""+",21)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(72,7, 7,0,"+"\"NULL\""+",21)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(73,5, 4,0,"+"\"NULL\""+",22)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(74,6, 5,0,"+"\"NULL\""+",22)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(75,7, 6,0,"+"\"NULL\""+",22)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(76,7, 7,0,"+"\"NULL\""+",22)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(77,5, 4,0,"+"\"NULL\""+",23)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(78,6, 5,0,"+"\"NULL\""+",23)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(79,7, 6,0,"+"\"NULL\""+",23)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(80,7, 7,0,"+"\"NULL\""+",23)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(81,1, 12,0,"+"\"Kriol LUK_4 1-13.mp3\""+",24)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(82,1, 12,0,"+"\"Kriol MRK_6 30-44 .mp3\""+",25)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(83,1, 5,0,"+"\"NULL\""+",26)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(84,1, 7,0,"+"\"NULL\""+",26)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(85,1, 10,0,"+"\"NULL\""+",26)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(86,1, 5,0,"+"\"NULL\""+",27)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(87,1, 7,0,"+"\"NULL\""+",27)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(88,1, 10,0,"+"\"NULL\""+",27)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(89,1, 5,0,"+"\"NULL\""+",28)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(90,1, 7,0,"+"\"NULL\""+",28)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(91,1, 10,0,"+"\"NULL\""+",28)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(92,1, 5,0,"+"\"NULL\""+",29)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(93,1, 7,0,"+"\"NULL\""+",29)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(94,1, 10,0,"+"\"NULL\""+",29)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(95,1, 5,0,"+"\"NULL\""+",30)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(96,1, 7,0,"+"\"NULL\""+",30)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(97,1, 10,0,"+"\"NULL\""+",30)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(97,1, 5,0,"+"\"NULL\""+",31)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(99,1, 7,0,"+"\"NULL\""+",31)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(100,1, 10,0,"+"\"NULL\""+",31)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(101,1, 5,0,"+"\"NULL\""+",32)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(102,1, 7,0,"+"\"NULL\""+",32)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(103,1, 10,0,"+"\"NULL\""+",32)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(104,1, 5,0,"+"\"NULL\""+",33)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(105,1, 7,0,"+"\"NULL\""+",33)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(106,1, 10,0,"+"\"NULL\""+",33)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(107,1, 5,0,"+"\"NULL\""+",34)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(108,1, 7,0,"+"\"NULL\""+",34)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(109,1, 10,0,"+"\"NULL\""+",34)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(110,1, 12,0,"+"\"Kriol MRK 5 1-20.mp3\""+",35)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(111,1, 12,0,"+"\"Kriol GEN_1&2 4.mp3\""+",36)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(112,1, 15,0,"+"\"Kriol MRK_4 35-41.mp3\""+",37)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(113,1, 15,0,"+"\"Kriol JONAH 2.mp3\""+",38)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(114,1, 15,0,"+"\"Kriol ACT_3 1-10.mp3\""+",39)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(115,1, 15,0,"+"\"Kriol EPH_6 10-20.mp3\""+",40)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(116,1, 15,0,"+"\"Kriol LUK_4 1-13.mp3\""+",41)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(117,1, 15,0,"+"\"Kriol MRK_6 30-44 .mp3\""+",42)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(118,1, 15,0,"+"\"Kriol MRK 5 1-20.mp3\""+",43)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5) VALUES(119,1, 15,0,"+"\"Kriol GEN_1&2 4.mp3\""+",44)")

        // There is no word table. They are in
    }
    //Functions for quering the DB

    fun getLevel(levelId:Int):Level{
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME WHERE $LEVEL_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(levelId.toString()))
        if (res.moveToNext()) {
            return Level(res.getInt(1),res.getString(2),res.getInt(3)==1,res.getString(4),res.getString(5))
        }
        return Level()
    }
    fun getPuzzle(puzzleId:Int):Puzzle{
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(puzzleId.toString()))
        if (res.moveToNext()) {
            return Puzzle(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4)==1,res.getString(5),res.getInt(6))
        }
        return Puzzle()
    }
    fun getActiveLevel():Level{
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME"
        val res = db.rawQuery(query,arrayOf())
        if (res.moveToNext()) {
            if(res.getInt(3)==0){
                return Level(res.getInt(1),res.getString(2),res.getInt(3)==1,res.getString(4),res.getString(5))
            }
        }
        return Level(-2)
    }
    fun getActivePuzzle(levelId:Int):Puzzle{
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_6=?"
        val res = db.rawQuery(query,arrayOf(levelId.toString()))
        if (res.moveToNext()) {
            if(res.getInt(4)==0){
                return Puzzle(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4)==1,res.getString(5),res.getInt(6))
            }
        }
        markLevelCompleted(levelId)
        return getActivePuzzle(levelId+1)
    }

    private fun markLevelCompleted(levelId: Int):Boolean {
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME WHERE $LEVEL_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(levelId.toString()))
        var lvl:Level
        if (res.moveToNext()) {
            lvl=Level(res.getInt(1),res.getString(2),res.getInt(3)==1,res.getString(4),res.getString(5))
        }else{
            lvl=Level()
        }
        if(lvl.id != -1 && lvl.id != -2 ){
            lvl.completed=true
            var set = ContentValues()
            set.put(LEVEL_COL_1,lvl.id)
            set.put(LEVEL_COL_2,lvl.title)
            set.put(LEVEL_COL_3,1)
            set.put(LEVEL_COL_4,lvl.picture)
            set.put(LEVEL_COL_5,lvl.word_file)
            db.update(LEVEL_TABLE_NAME,set,"$LEVEL_COL_1=?", arrayOf(levelId.toString()))
            return true
        } else{
            return false
        }
    }


    companion object {
        //level vals
        const val LEVEL_TABLE_NAME = "level"
        const val LEVEL_COL_1="id"
        const val LEVEL_COL_2="title"
        const val LEVEL_COL_3="completed_ind"
        const val LEVEL_COL_4="banner_picture"
        const val LEVEL_COL_5="word_file"
        //puzzle vals
        const val PUZZLE_TABLE_NAME = "puzzle"
        const val PUZZLE_COL_1="id"
        const val PUZZLE_COL_2="size"
        const val PUZZLE_COL_3="num_of_words"
        const val PUZZLE_COL_4="completed_ind"
        const val PUZZLE_COL_5="audio_file"
        const val PUZZLE_COL_6="level_id"

        private const val versionNumber = 1
    }
}