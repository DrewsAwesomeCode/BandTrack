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
        private val KEY_HOTEL = "hotel"
        private val KEY_CITY = "city"
        private val KEY_HOURS = "hours"
        private val KEY_GIFTS = "gifts"
        private val KEY_EVENTS = "events"
        private val KEY_CONSIDERATION = "consideration"
        private val KEY_GIFTCONSIDERATION = "giftConsideration"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_SET_TABLE = ("CREATE TABLE " + TABLE_SETS + "("
                + KEY_SETID + " INTEGER PRIMARY KEY,"
                + KEY_BANDNAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_HOTEL + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_HOURS + " TEXT,"
                + KEY_GIFTS + " TEXT,"
                + KEY_EVENTS + " TEXT,"
                + KEY_CONSIDERATION + " TEXT,"
                + KEY_GIFTCONSIDERATION + " TEXT"
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
        contentValues.put(KEY_SETID, set.dId)
        contentValues.put(KEY_BANDNAME, set.bandName)
        contentValues.put(KEY_DATE, set.date.toString())
        contentValues.put(KEY_HOTEL, set.hotel)
        contentValues.put(KEY_CITY, set.city)
        contentValues.put(KEY_HOURS, set.hours)
        contentValues.put(KEY_GIFTS, set.gifts )
        contentValues.put(KEY_EVENTS, set.events)
        contentValues.put(KEY_CONSIDERATION, set.consideration)
        contentValues.put(KEY_GIFTCONSIDERATION, set.giftConsideration)

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
        var dId: Int
        var bandName: String
        var date: String
        var hotel: String
        var city: String
        var hours: String
        var gifts: String
        var events: String
        var consideration: String
        var giftConsideration: String

        cursor.moveToFirst()
        if (cursor.moveToFirst()) {
            do {
                dId = cursor.getInt(cursor.getColumnIndex("id"))
                bandName = cursor.getString(cursor.getColumnIndex("name"))
                date = cursor.getString(cursor.getColumnIndex("date"))
                hotel = cursor.getString(cursor.getColumnIndex("hotel"))
                city = cursor.getString(cursor.getColumnIndex("city"))
                hours = cursor.getString(cursor.getColumnIndex("hours"))
                gifts = cursor.getString(cursor.getColumnIndex("gifts"))
                events = cursor.getString(cursor.getColumnIndex("events"))
                consideration = cursor.getString(cursor.getColumnIndex("consideration"))
                giftConsideration = cursor.getString(cursor.getColumnIndex("giftConsideration"))

                val meet= Set(
                    dId = dId,
                    bandName = bandName,
                    date = Date.valueOf(date),
                    hotel = hotel,
                    city = city,
                    hours = hours.toDouble(),
                    gifts = gifts,
                    events = events,
                    consideration = consideration.toLong(),
                    giftConsideration = giftConsideration.toLong()
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
        contentValues.put(KEY_SETID, set.dId)
        contentValues.put(KEY_BANDNAME, set.bandName)
        contentValues.put(KEY_DATE, set.date.toString())
        contentValues.put(KEY_HOTEL, set.hotel)
        contentValues.put(KEY_CITY, set.city)
        contentValues.put(KEY_HOURS, set.hours)
        contentValues.put(KEY_GIFTS, set.gifts)
        contentValues.put(KEY_EVENTS, set.events)
        contentValues.put(KEY_CONSIDERATION, set.consideration)
        contentValues.put(KEY_GIFTCONSIDERATION, set.giftConsideration)


        // Updating Row
        val success = db.update(DatabaseHandler.TABLE_SETS, contentValues,"id="+set.dId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete sets
    fun deleteSet(set: Set):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.KEY_SETID, set.dId)
        // Deleting Row
        val success = db.delete(DatabaseHandler.TABLE_SETS,"id="+set.dId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}