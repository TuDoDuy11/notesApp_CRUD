package com.example.notesapp_crud.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AuthDatabaseHelper(private val context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_USERNAME TEXT,$COLUMN_PASSWORD TEXT)"
        p0?.execSQL(createTableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(dropTableQuery)
        onCreate(p0)
    }

    fun insertUser(username:String, password:String):Long{
        val values = ContentValues().apply {
            put(COLUMN_USERNAME,username)
            put(COLUMN_PASSWORD,password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME,null,values)
    }

    fun readUser(username: String,password:String):Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username,password)
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
}