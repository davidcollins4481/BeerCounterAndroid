package com.example.david.beercounter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BeerDBHelper(context: Context) : SQLiteOpenHelper(context, "Consumption.db", null, 1) {
    companion object {
        public val TABLE: String = "Entries"
        public val TIMESTAMP: String = "TIMESTAMP"
        public val ID: String = "_id"
        public val DRINKTYPE: String = "DRINK_TYPE"
    }

    private val DEFAULT_DRINKTYPE = 1
    val DATABASE_CREATE =
        "CREATE TABLE if not exists " + TABLE + " (" +
            "${ID} integer PRIMARY KEY autoincrement," +
            "${TIMESTAMP} integer" +
            "," +
            "${DRINKTYPE} integer"+
            ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
    }

    fun addDrink(type: Int = DEFAULT_DRINKTYPE) {
        val values = ContentValues()
        values.put(DRINKTYPE, type)
        values.put(TIMESTAMP, System.currentTimeMillis())
        getWritableDatabase().insert(TABLE, null, values);
    }
}