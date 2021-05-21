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

class Database(context: Context) :
    SQLiteOpenHelper(context, context.getString(R.string.app_name)+".db", null, versionNumber){

    private val lvlPuzMap = mapOf(1 to 4,2 to 4,3 to 4,4 to 4, 5 to 4, 6 to 4, 7 to 4, 8 to 4, 9 to 1,
        10 to 1, 11 to 1, 12 to 1, 13 to 4, 14 to 4, 15 to 4, 16 to 4, 17 to 4, 18 to 4, 19 to 4,
        20 to 4, 21 to 4, 22 to 4, 23 to 4, 24 to 1, 25 to 1, 26 to 3, 27 to 3, 28 to 3, 29 to 3,
        30 to 3, 31 to 3, 32 to 3, 33 to 3, 34 to 3, 35 to 1, 36 to 1, 37 to 1, 38 to 1, 39 to 1,
        40 to 1, 41 to 1, 42 to 1, 43 to 1, 44 to 1)

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
            "create table %s ( %s INTEGER PRIMARY KEY,%s TEXT,%s INTEGER,%s TEXT,%s TEXT, %s INTEGER)",
            LEVEL_TABLE_NAME,
            LEVEL_COL_1,
            LEVEL_COL_2,
            LEVEL_COL_3,
            LEVEL_COL_4,
            LEVEL_COL_5,
            LEVEL_COL_6
        )
        db.execSQL(lessonTableCreate)
        val puzzleTableCreate = String.format(
            "create table %s ( %s INTEGER PRIMARY KEY,%s INTEGER,%s INTEGER,%s INTEGER,%s TEXT,%s INTEGER,%s INTEGER,%s INTEGER,FOREIGN KEY(%s) REFERENCES %s(%s))",
            PUZZLE_TABLE_NAME,
            PUZZLE_COL_1,
            PUZZLE_COL_2,
            PUZZLE_COL_3,
            PUZZLE_COL_4,
            PUZZLE_COL_5,
            PUZZLE_COL_6,
            PUZZLE_COL_7,
            PUZZLE_COL_8,
            PUZZLE_COL_6,
            LEVEL_TABLE_NAME,
            LEVEL_COL_1
        )
        db.execSQL(puzzleTableCreate)
    }
    private fun fillTables(db: SQLiteDatabase) {
        //Insert into the Level Table
        val levelString = "INSERT INTO $LEVEL_TABLE_NAME ( $LEVEL_COL_1, $LEVEL_COL_2, $LEVEL_COL_3,$LEVEL_COL_4,$LEVEL_COL_5,$LEVEL_COL_6 ) VALUES"

        db.execSQL(levelString+"(1,\"Jisas bin stapam win en weib(Mak 4:35-41)\", 0, "+"\"mak4\" , "+"\"mark_4_35_41_a\", 4)")
        db.execSQL(levelString+"(2,\"Jisas bin weikimap Lesaras brom dedbala(Jon 11:38-44)\", 0, "+"\"lazarus\" , "+"\"john_11_38_44_a\", 4)")
        db.execSQL(levelString+"(3,\"Jisas bin bon(Methyu 1:18-25)\", 0, "+"\"methyu1\" , "+"\"matthew_1_18_25_a\", 4)")
        db.execSQL(levelString+"(4,\"Jona bin jingat langa God(Jona 2:1-10)\", 0, "+"\"jona\" , "+"\"jonah_2_1_10_a\", 4)")
        db.execSQL(levelString+"(5,\"Pida bin meigim wan leimbala men gudwan(Eks 3:1-10)\", 0, "+"\"eks3\" , "+"\"acts_3_1_10_a\", 4)")
        db.execSQL(levelString+"(6,\"Josef en im braja olabat(Jenasis 37:1-11)\", 0, "+"\"jenasis37\" , "+"\"genesis_37_1_11_a\", 4)")
        db.execSQL(levelString+"(7,\"Thribala fishamen(Lul 5:1-11)\", 0, "+"\"luk5\" , "+"\"luke_5_1_11_a\", 4)")
        db.execSQL(levelString+"(8,\"Detlot speshalwan klos blanga ola Kristjan pipul(Ifishans 6:10-20)\", 0, "+"\"ifishans\" , "+"\"ephesians_6_10_20_a\", 4)")
        db.execSQL(levelString+"(9,\"Jisas bin stapam win en weib(Mak 4:35-41)\", 0, "+"\"mak4\" , "+"\"mark_4_35_41_a\", 1)")
        db.execSQL(levelString+"(10,\"Jona bin jingat langa God(Jona 2:1-10)\", 0, "+"\"jona\" , "+"\"jonah_2_1_10_a\", 1)")
        db.execSQL(levelString+"(11,\"Pida bin meigim wan leimbala men gudwan(Eks 3:1-10)\", 0, "+"\"eks3\" , "+"\"acts_3_1_10_a\", 1)")
        db.execSQL(levelString+"(12,\"Detlot speshalwan klos blanga ola Kristjan pipul(Ifishans 6:10-20)\", 0, "+"\"ifishans\" , "+"\"ephesians_6_10_20_a\", 1)")
        db.execSQL(levelString+"(13,\"Raida langa waitwan hos(Rebaleishan 19:11-21)\", 0, "+"\"rebaleishan\" , "+"\"revelation_19_11_21_a\", 4)")
        db.execSQL(levelString+"(14,\"Detlot 7 san blanga Seba(Eks 19:11-22)\", 0, "+"\"ephesus\" , "+"\"acts_19_11_22_a\", 4)")
        db.execSQL(levelString+"(15,\"Pol bin prei blanga olabat(Kaloshans 1:3-14)\", 0, "+"\"kaloshans\" , "+"\"collossians_1_3_14_a\", 4)")
        db.execSQL(levelString+"(16,\"Wan lowamen bin wandi trikim Jisas(Luk 10:25-37)\", 0, "+"\"luk10\" , "+"\"luke_10_25_37_a\", 4)")
        db.execSQL(levelString+"(17,\"Seitin bin temtimbat Jisas(Luk 4:1-13)\", 0, "+"\"temptation\" , "+"\"luke_4_1_13_a\", 4)")
        db.execSQL(levelString+"(18,\"Jisas en im braja olabat(Hibrus 2:5-18)\", 0, "+"\"fespida3\" , "+"\"hebrews_2_5_18_a\", 4)")
        db.execSQL(levelString+"(19,\"Teiknodis langa det trubalawan wed(Kaloshans 2:6-19)\", 0, "+"\"fespida3\" , "+"\"collosians_2_6_19_a\", 4)")
        db.execSQL(levelString+"(20,\"Joshuwa bin bidim ola Emarait pipul(Joshuwa 10:1-15)\", 0, "+"\"joshuwa\" , "+"\"joshua_10_1_15_a\", 4)")
        db.execSQL(levelString+"(21,\"Jisas bin fidim 5,000 men(Mak 6:30-44)\", 0, "+"\"mak6\" , "+"\"mark_6_30_44_a\", 4)")
        db.execSQL(levelString+"(22,\"God na meigim wi laigim gija(Fes Jon 4:7-21)\", 0, "+"\"lovelight\" , "+"\"john1_4_7_21_a\", 4)")
        db.execSQL(levelString+"(23,\"Kristjan pipul garra abum trabul blanga gudbala ting (Fes Pida 3:8-22)\", 0, "+"\"fespida3\" , "+"\"peter1_3_8_22_a\", 4)")
        db.execSQL(levelString+"(24,\"Seitin bin temtimbat Jisas(Luk 4:1-13)\", 0, "+"\"temptation\" , "+"\"luke_4_1_13_a\", 1)")
        db.execSQL(levelString+"(25,\"Jisas bin fidim 5,000 men(Mak 6:30-44)\", 0, "+"\"mak6\" , "+"\"mark_6_30_44_a\", 1)")
        db.execSQL(levelString+"(26,\"Nogudbala spirit en bigibigi(Mak 5:1-20)\", 0, "+"\"mak5\" , "+"\"mark_5_1_20_a\", 3)")
        db.execSQL(levelString+"(27,\"Mosis en ola pipul bin preisim God(Eksadas 15:1-21)\", 0, "+"\"eksadas15\" , "+"\"exodus_15_1_21_a\", 3)")
        db.execSQL(levelString+"(28,\"Nikadimas bin kaman langa Jisas(Jon 3:1-21)\", 0, "+"\"nicodemus\" , "+"\"john_3_1_21_a\", 3)")
        db.execSQL(levelString+"(29,\"God bin kilim ola feswan san(Eksadas 12:21-42)\", 0, "+"\"eksadas12\" , "+"\"exodus_12_21_42_a\", 3)")
        db.execSQL(levelString+"(30,\"Dubala bin dagat daga(Jenasis 3:1-24)\", 0, "+"\"jenasis3\" , "+"\"gen_3_1_24_a\", 3)")
        db.execSQL(levelString+"(31,\"Denyul langa det hol weya ola laiyan oldei jidan(Denyul 6:1-28)\", 0, "+"\" denyul6\" , "+"\"daniel_6_1_28_a\", 3)")
        db.execSQL(levelString+"(32,\"God bin meigim ebrijing(Jenasis 1:1-2:3)\", 0, "+"\"jenasis1\" , "+"\"genesis_1_2_1_4_a\", 3)")
        db.execSQL(levelString+"(33,\"Ilaija en detlot mesinja blanga Beil(Fes Kings 18:1-40)\", 0, "+"\"elijah\" , "+"\"kings1_18_1_40_a\", 3)")
        db.execSQL(levelString+"(34,\"Deibid bin bidim Galaiyath(Fes Semyul 17:1-58)\", 0, "+"\"david\" , "+"\"samuel1_17_1_58_a\", 3)")
        db.execSQL(levelString+"(35,\"Nogudbala spirit en bigibigi(Mak 5:1-20)\", 0, "+"\"mak5\" , "+"\"mark_5_1_20_a\", 1)")
        db.execSQL(levelString+"(36,\"God bin meigim ebrijing(Jenasis 1:1-2:3)\", 0, "+"\"jenasis1\" , "+"\"genesis_1_2_1_4_a\", 1)")
        db.execSQL(levelString+"(37,\"Jisas bin stapam win en weib(Mak 4:35-41)\", 0, "+"\"mak4\" , "+"\"mark_4_35_41_a\", 1)")
        db.execSQL(levelString+"(38,\"Jona bin jingat langa God(Jona 2:1-10)\", 0, "+"\"jona\" , "+"\"jonah_2_1_10_a\", 1)")
        db.execSQL(levelString+"(39,\"Pida bin meigim wan leimbala men gudwan(Eks 3:1-10)\", 0, "+"\"eks3\" , "+"\"acts_3_1_10_a\", 1)")
        db.execSQL(levelString+"(40,\"Detlot speshalwan klos blanga ola Kristjan pipul(Ifishans 6:10-20)\", 0, "+"\"ifishans\" , "+"\"ephesians_6_10_20_a\", 1)")
        db.execSQL(levelString+"(41,\"Seitin bin temtimbat Jisas(Luk 4:1-13)\", 0, "+"\"temptation\" , "+"\"luke_4_1_13_a\", 1)")
        db.execSQL(levelString+"(42,\"Jisas bin fidim 5,000 men(Mak 6:30-44)\", 0, "+"\"mak6\" , "+"\"mark_6_30_44_a\", 1)")
        db.execSQL(levelString+"(43,\"Nogudbala spirit en bigibigi(Mak 5:1-20)\", 0, "+"\"mak5\" , "+"\"mark_5_1_20_a\", 1)")
        db.execSQL(levelString+"(44,\"God bin meigim ebrijing(Jenasis 1:1-2:3)\", 0, "+"\"jenasis1\" , "+"\"genesis_1_2_1_4_a\", 1)")

        //Insert Into the Puzzle Tables
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(1,3 , 2,0,"+"\"NULL\""+",1,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(2,4 , 3,0,"+"\"NULL\""+",1,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(3,5 , 4,0,"+"\"NULL\""+",1,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(4,5 , 5,0,"+"\"NULL\""+",1,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(5,3 , 2,0,"+"\"NULL\""+",2,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(6,4 , 3,0,"+"\"NULL\""+",2,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(7,5 , 4,0,"+"\"NULL\""+",2,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(8,5 , 5,0,"+"\"NULL\""+",2,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(9,3 , 2,0,"+"\"NULL\""+",3,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(10,4 , 3,0,"+"\"NULL\""+",3,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(11,5 , 4,0,"+"\"NULL\""+",3,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(12,5 , 5,0,"+"\"NULL\""+",3,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(13,3 , 2,0,"+"\"NULL\""+",4,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(14,4 , 3,0,"+"\"NULL\""+",4,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(15,5 , 4,0,"+"\"NULL\""+",4,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(16,5 , 5,0,"+"\"NULL\""+",4,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(17,3 , 2,0,"+"\"NULL\""+",5,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(18,4 , 3,0,"+"\"NULL\""+",5,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(19,5 , 4,0,"+"\"NULL\""+",5,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(20,5 , 5,0,"+"\"NULL\""+",5,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(21,3 , 2,0,"+"\"NULL\""+",6,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(22,4 , 3,0,"+"\"NULL\""+",6,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(23,5 , 4,0,"+"\"NULL\""+",6,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(24,5 , 5,0,"+"\"NULL\""+",6,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(25,3 , 2,0,"+"\"NULL\""+",7,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(26,4 , 3,0,"+"\"NULL\""+",7,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(27,5 , 4,0,"+"\"NULL\""+",7,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(28,5 , 5,0,"+"\"NULL\""+",7,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(29,3 , 2,0,"+"\"NULL\""+",8,3,3)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(30,4 , 3,0,"+"\"NULL\""+",8,4,4)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(31,5 , 4,0,"+"\"NULL\""+",8,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(32,5 , 5,0,"+"\"NULL\""+",8,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(33,8 , 12,0,"+"\"kriol_mrk_4_35_41\""+",9,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(34,8 , 12,0,"+"\"kriol_jonah_2\""+",10,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(35,8 , 12,0,"+"\"kriol_act_3_1_10\""+",11,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(36,8 , 12,0,"+"\"kriol_eph_6_10_20\""+",12,3, 5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(37,5 , 4,0,"+"\"NULL\""+",13,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(38,6 , 5,0,"+"\"NULL\""+",13,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(39,7 , 6,0,"+"\"NULL\""+",13,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(40,7 , 7,0,"+"\"NULL\""+",13,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(41,5 , 4,0,"+"\"NULL\""+",14,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(42,6 , 5,0,"+"\"NULL\""+",14,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(43,7 , 6,0,"+"\"NULL\""+",14,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(44,7 , 7,0,"+"\"NULL\""+",14,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(45,5 , 4,0,"+"\"NULL\""+",15,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(46,6 , 5,0,"+"\"NULL\""+",15,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(47,7 , 6,0,"+"\"NULL\""+",15,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(48,7 , 7,0,"+"\"NULL\""+",15,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(49,5 , 4,0,"+"\"NULL\""+",16,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(50,6 , 5,0,"+"\"NULL\""+",16,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(51,7 , 6,0,"+"\"NULL\""+",16,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(52,7 , 7,0,"+"\"NULL\""+",16,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(53,5 , 4,0,"+"\"NULL\""+",17,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(54,6 , 5,0,"+"\"NULL\""+",17,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(55,7 , 6,0,"+"\"NULL\""+",17,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(56,7 , 7,0,"+"\"NULL\""+",17,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(57,5 , 4,0,"+"\"NULL\""+",18,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(58,6 , 5,0,"+"\"NULL\""+",18,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(59,7 , 6,0,"+"\"NULL\""+",18,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(60,7 , 7,0,"+"\"NULL\""+",18,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(61,5 , 4,0,"+"\"NULL\""+",19,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(62,6 , 5,0,"+"\"NULL\""+",19,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(63,7 , 6,0,"+"\"NULL\""+",19,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(64,7 , 7,0,"+"\"NULL\""+",19,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(65,5 , 4,0,"+"\"NULL\""+",20,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(66,6 , 5,0,"+"\"NULL\""+",20,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(67,7 , 6,0,"+"\"NULL\""+",20,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(68,7 , 7,0,"+"\"NULL\""+",20,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(69,5 , 4,0,"+"\"NULL\""+",21,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(70,6 , 5,0,"+"\"NULL\""+",21,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(71,7 , 6,0,"+"\"NULL\""+",21,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(72,7 , 7,0,"+"\"NULL\""+",21,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(73,5 , 4,0,"+"\"NULL\""+",22,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(74,6 , 5,0,"+"\"NULL\""+",22,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(75,7 , 6,0,"+"\"NULL\""+",22,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(76,7 , 7,0,"+"\"NULL\""+",22,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(77,5 , 4,0,"+"\"NULL\""+",23,5,5)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(78,6 , 5,0,"+"\"NULL\""+",23,6,6)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(79,7 , 6,0,"+"\"NULL\""+",23,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(80,7 , 7,0,"+"\"NULL\""+",23,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(81,8 , 12,0,"+"\"kriol_luk_4_1_13\""+",24,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(82,8 , 12,0,"+"\"kriol_mrk_6_30_44\""+",25,5, 7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(83,10 , 5,0,"+"\"NULL\""+",26,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(84,10 , 7,0,"+"\"NULL\""+",26,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(85,10 , 10,0,"+"\"NULL\""+",26,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(86,10 , 5,0,"+"\"NULL\""+",27,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(87,10 , 7,0,"+"\"NULL\""+",27,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(88,10 , 10,0,"+"\"NULL\""+",27,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(89,10 , 5,0,"+"\"NULL\""+",28,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(90,10 , 7,0,"+"\"NULL\""+",28,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(91,10 , 10,0,"+"\"NULL\""+",28,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(92,10 , 5,0,"+"\"NULL\""+",29,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(93,10 , 7,0,"+"\"NULL\""+",29,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(94,10 , 10,0,"+"\"NULL\""+",29,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(95,10 , 5,0,"+"\"NULL\""+",30,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(96,10 , 7,0,"+"\"NULL\""+",30,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(97,10 , 10,0,"+"\"NULL\""+",30,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(98,10 , 5,0,"+"\"NULL\""+",31,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(99,10 , 7,0,"+"\"NULL\""+",31,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(100,10 , 10,0,"+"\"NULL\""+",31,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(101,10 , 5,0,"+"\"NULL\""+",32,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(102,10 , 7,0,"+"\"NULL\""+",32,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(103,10 , 10,0,"+"\"NULL\""+",32,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(104,10 , 5,0,"+"\"NULL\""+",33,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(105,10 , 7,0,"+"\"NULL\""+",33,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(106,10 , 10,0,"+"\"NULL\""+",33,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(107,10 , 5,0,"+"\"NULL\""+",34,7,7)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(108,10 , 7,0,"+"\"NULL\""+",34,7 , 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(109,10 , 10,0,"+"\"NULL\""+",34,7, 10)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(110,8 , 12,0,"+"\"kriol_mrk_5_1_20\""+",35,7, 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(111,8 , 12,0,"+"\"kriol_gen_1_2_4\""+",36,7, 8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(112,8 , 12,0,"+"\"kriol_mrk_4_35_41\""+",37,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(113,8 , 12,0,"+"\"kriol_jonah_2\""+",38,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(114,8 , 12,0,"+"\"kriol_act_3_1_10\""+",39,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(115,8 , 12,0,"+"\"kriol_eph_6_10_20\""+",40,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(116,8 , 12,0,"+"\"kriol_luk_4_1_13\""+",41,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(117,8 , 12,0,"+"\"kriol_mrk_6_30_44\""+",42,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(118,8 , 12,0,"+"\"kriol_mrk_5_1_20\""+",43,3,8)")
        db.execSQL("INSERT INTO $PUZZLE_TABLE_NAME ( $PUZZLE_COL_1, $PUZZLE_COL_2, $PUZZLE_COL_3,$PUZZLE_COL_4,$PUZZLE_COL_5,$PUZZLE_COL_6,$PUZZLE_COL_7,$PUZZLE_COL_8) VALUES(119,8 , 12,0,"+"\"kriol_gen_1_2_4\""+",44,3,8)")


        // There is no word table. They are in files
    }
    //Functions for querying the DB

    fun getLevel(levelId:Int):Level{
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME WHERE $LEVEL_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(levelId.toString()))
        var level = Level()
        if (res.moveToNext()) {
            level = Level(res.getInt(0),res.getString(1),res.getInt(2)==1,res.getString(3),res.getString(4))
        }
        res.close()
        return level
    }
    fun getLevelList():ArrayList<Level>{
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME"
        val res = db.rawQuery(query, arrayOf())
        val returnList = ArrayList<Level>()
        while (res.moveToNext()) {
            returnList.add( Level(res.getInt(0),res.getString(1),res.getInt(2)==1,res.getString(3),res.getString(4)))
        }
        res.close()
        return returnList
    }

    fun getPuzzleList():ArrayList<Puzzle>{
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME"
        val res = db.rawQuery(query, arrayOf())
        val returnList = ArrayList<Puzzle>()
        while(res.moveToNext()){
            returnList.add(
                Puzzle(
                    res.getInt(0),
                    res.getInt(1),
                    res.getInt(2),
                    res.getInt(3) == 1,
                    res.getString(4),
                    res.getInt(5),
                    res.getInt(6),
                    res.getInt(7)
                )
            )
        }
        res.close()
        return returnList
    }


    fun getLvlPuzzleList(levelId: Int): ArrayList<Puzzle> {

        val fullList = this.getPuzzleList()
        val levelList = this.getLevelList()

        var firstPuzzleID: Int = 1
        for (lev in levelList) {
            if (lev.id == levelId) {
                break
            }
            firstPuzzleID += this.lvlPuzMap.getValue(lev.id)
        }

        val numPuzzles: Int = this.lvlPuzMap.getValue(levelId)
        var returnList = ArrayList<Puzzle>()
        for (i in 1..numPuzzles) {
            returnList.add(fullList[firstPuzzleID - 1])
            firstPuzzleID++
        }
        return returnList
    }


    fun getPuzzle(puzzleId:Int):Puzzle{
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(puzzleId.toString()))
        var puzzle = Puzzle()
        if (res.moveToNext()) {
            puzzle = Puzzle(res.getInt(0),res.getInt(1),res.getInt(2),res.getInt(3)==1,res.getString(4),res.getInt(5),res.getInt(6),res.getInt(7))
        }
        res.close()
        return puzzle
    }
    fun getActiveLevel():Level{
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME"
        val res = db.rawQuery(query,arrayOf())
        var level = Level(1)
        //stops at first incomplete level. will break if levels can be completed out of order
        while (res.moveToNext()) {
            if(res.getInt(2)==0){
                level = Level(res.getInt(0),res.getString(1),res.getInt(2)==1,res.getString(3),res.getString(4))
                return level
            }
        }
        //if no incomplete levels are found, returns the last level
        res.moveToPrevious()
        level = Level(res.getInt(0),res.getString(1),res.getInt(2)==1,res.getString(3),res.getString(4))
        return level
    }

    fun getActivePuzzle(levelId:Int):Puzzle{
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_6 = ?"
        val res = db.rawQuery(query,arrayOf(levelId.toString()))
        var puzz = Puzzle()
        while (res.moveToNext()) {
            if(res.getInt(3)==0){
                puzz = Puzzle(res.getInt(0),res.getInt(1),res.getInt(2),res.getInt(3)==1,res.getString(4),res.getInt(5),res.getInt(6),res.getInt(7))
                res.close()
                return puzz
            }
        }
        return puzz
        /*
        var pList: ArrayList<Puzzle> = this.getLvlPuzzleList(levelId)
        var lvlCompleted: Boolean = true
        for (puz in pList) {
            if (! puz.completed) {
                lvlCompleted = false
            }
        }
        */
        markLevelCompleted(levelId)
        return getActivePuzzle(levelId+1)
    }
    fun getActivePuzzleNum():Int{
        val temp = getActivePuzzle(getActiveLevel().id).id
        return temp
    }
    fun markLevelCompleted(levelId: Int):Boolean {
        val db = this.writableDatabase
        val query = "select * from $LEVEL_TABLE_NAME WHERE $LEVEL_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(levelId.toString()))
        var lvl:Level
        if (res.moveToNext()) {
            lvl=Level(res.getInt(0),res.getString(1),res.getInt(2)==1,res.getString(3),res.getString(4))
        }else{
            lvl=Level()
        }
        if(lvl.id != -1 && lvl.id != -2 ){
            var set = ContentValues()
            set.put(LEVEL_COL_1,lvl.id)
            set.put(LEVEL_COL_2,lvl.title)
            set.put(LEVEL_COL_3,1)
            set.put(LEVEL_COL_4,lvl.picture)
            set.put(LEVEL_COL_5,lvl.word_file)
            db.update(LEVEL_TABLE_NAME,set,"$LEVEL_COL_1=?", arrayOf(levelId.toString()))
            res.close()
            return true
        } else{
            res.close()
            return false
        }
    }

    //returns true if the entire level is now complete
    fun markPuzzleCompleted(puzzleId: Int):Boolean {
        val db = this.writableDatabase
        val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(puzzleId.toString()))
        var puz:Puzzle
        if (res.moveToNext()) {
            puz=Puzzle(res.getInt(0),res.getInt(1),res.getInt(2),res.getInt(3)==1,res.getString(4),res.getInt(5),res.getInt(6),res.getInt(7))
        }else{
            puz=Puzzle()
        }
        if(puz.id != -1 && puz.id != -2 ){
            puz.completed=true
            var set = ContentValues()
            set.put(PUZZLE_COL_1,puz.id)
            set.put(PUZZLE_COL_2,puz.size)
            set.put(PUZZLE_COL_3,puz.numOfWords)
            set.put(PUZZLE_COL_4,1)
            set.put(PUZZLE_COL_5,puz.audioFile)
            set.put(PUZZLE_COL_6,puz.level_id)
            set.put(PUZZLE_COL_7,puz.min_num_letters)
            set.put(PUZZLE_COL_8,puz.max_num_letters)
            db.update(PUZZLE_TABLE_NAME,set,"$PUZZLE_COL_1=?", arrayOf(puzzleId.toString()))
            val next = getPuzzle(puz.id+1)

            //TODO finish level completion
            val query = "select * from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_6 = ? "
            val res = db.rawQuery(query, arrayOf(puz.level_id.toString()))
            //check if all puzzles of level are complete
            while(res.moveToNext()){
                if(res.getInt(3) == 0){
                    res.close()
                    return false
                }
            }
            res.close()
            return markLevelCompleted(puz.level_id)

        } else{
            res.close()
            return false
        }
    }

    fun isAudioPuzzle(puzzleId: Int):Boolean {
        val db = this.writableDatabase
        val query = "select $PUZZLE_COL_5 from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(puzzleId.toString()))
        if (res.moveToNext()) {
            val ret = res.getString(0)!="NULL"
            res.close()
            return ret
        }else {
            res.close()
            return false
        }
    }

    //returns the LevelID that the puzzle belongs to
    fun getLevelIDFromPuzzleID(puzzleID: Int):Int {
        val db = this.writableDatabase
        val query = "select $PUZZLE_COL_6 from $PUZZLE_TABLE_NAME WHERE $PUZZLE_COL_1 = ? "
        val res = db.rawQuery(query, arrayOf(puzzleID.toString()))
        var id = -1
        if(res.moveToNext()){
            id = res.getString(0).toInt()
        }
        res.close()
        return id
    }

    fun getPuzzleIndInLevel(puzzleID: Int):Int {
        val db = this.writableDatabase
        val query = "select $LEVEL_COL_6 from $LEVEL_TABLE_NAME"
        val res = db.rawQuery(query, arrayOf())
        //count backwords, subtracting off the levels
        var count = puzzleID
        while(res.moveToNext()) {
            count -= res.getString(0).toInt()
            if(count <= 0) break
        }
        count += res.getString(0).toInt()
        res.close()
        return count
    }

    companion object {
        //level vals
        const val LEVEL_TABLE_NAME = "level"
        const val LEVEL_COL_1="id"
        const val LEVEL_COL_2="title"
        const val LEVEL_COL_3="completed_ind"
        const val LEVEL_COL_4="banner_picture"
        const val LEVEL_COL_5="word_file"
        const val LEVEL_COL_6="num_puzzles"
        //puzzle vals
        const val PUZZLE_TABLE_NAME = "puzzle"
        const val PUZZLE_COL_1="id"
        const val PUZZLE_COL_2="size"
        const val PUZZLE_COL_3="num_of_words"
        const val PUZZLE_COL_4="completed_ind"
        const val PUZZLE_COL_5="audio_file"
        const val PUZZLE_COL_6="level_id"
        const val PUZZLE_COL_7="min_word_length"
        const val PUZZLE_COL_8="max_word_length"

        private const val versionNumber = 1
    }
}