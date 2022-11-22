package com.example.bandtrack.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import java.sql.Date

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "SET_DATABASE"
        private val TABLE_SETS = "set_table"

        private val KEY_SETID = "id"
        private val KEY_BANDNAME = "name"
        private val KEY_DATE = "date"
        private val KEY_SPOT = "spot"
        private val KEY_RATING = "rating"
        private val KEY_VENUE = "venue"
        private val KEY_CITY = "city"
        private val KEY_TICKETCOST = "ticketcost"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_SET_TABLE = ("CREATE TABLE " + TABLE_SETS + "("
                + KEY_SETID + " INTEGER PRIMARY KEY,"
                + KEY_BANDNAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_SPOT + " TEXT,"
                + KEY_RATING + " TEXT,"
                + KEY_VENUE + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_TICKETCOST + " TEXT"
                + ")")
        db?.execSQL(CREATE_SET_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETS)
        onCreate(db)
    }

    //method to insert sets
    fun addSet(set: Set):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_SETID, set.setId)
        contentValues.put(KEY_BANDNAME, set.bandName)
        contentValues.put(KEY_DATE, set.date.toString())
        contentValues.put(KEY_SPOT, set.spot)
        contentValues.put(KEY_RATING, set.rating)
        contentValues.put(KEY_VENUE, set.venue)
        contentValues.put(KEY_CITY, set.city)
        contentValues.put(KEY_TICKETCOST, set.ticketCost )

        // Inserting Row
        val success = db.insert(TABLE_SETS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read sets
    fun viewSet():List<Set>{
        val setList:ArrayList<Set> = ArrayList<Set>()
        val selectQuery = "SELECT  * FROM ${TABLE_SETS}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var setId: Int
        var bandName: String
        var date: String
        var spot: String
        var rating: String
        var venue: String
        var city: String
        var ticketCost: String

        cursor.moveToFirst()
        if (cursor.moveToFirst()) {
            do {
                setId = cursor.getInt(cursor.getColumnIndex("id"))
                bandName = cursor.getString(cursor.getColumnIndex("name"))
                date = cursor.getString(cursor.getColumnIndex("date"))
                spot = cursor.getString(cursor.getColumnIndex("spot"))
                rating = cursor.getString(cursor.getColumnIndex("rating"))
                venue = cursor.getString(cursor.getColumnIndex("venue"))
                city = cursor.getString(cursor.getColumnIndex("city"))
                ticketCost = cursor.getString(cursor.getColumnIndex("ticketcost"))

                val meet= Set(
                    setId = setId,
                    bandName = bandName,
                    date = Date.valueOf(date),
                    spot = spot,
                    rating = rating.toDouble(),
                    venue = venue,
                    city = city,
                    ticketCost = ticketCost.toDouble()
                )
                setList.add(meet)
            } while (cursor.moveToNext())
        }
        return setList
    }
    //method to update sets
    fun updateSet(set: Set):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_SETID, set.setId)
        contentValues.put(KEY_BANDNAME, set.bandName)
        contentValues.put(KEY_DATE, set.date.toString())
        contentValues.put(KEY_SPOT, set.spot)
        contentValues.put(KEY_RATING, set.rating)
        contentValues.put(KEY_VENUE, set.venue)
        contentValues.put(KEY_CITY, set.city)
        contentValues.put(KEY_TICKETCOST, set.ticketCost)


        // Updating Row
        val success = db.update(DatabaseHandler.TABLE_SETS, contentValues,"id="+set.setId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete sets
    fun deleteSet(set: Set):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.KEY_SETID, set.setId)
        // Deleting Row
        val success = db.delete(DatabaseHandler.TABLE_SETS,"id="+set.setId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}