package ru.demchuk.request.Model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbBinManager(context : Context) {
    private val dbBinHelper = DbBinHelper(context)
    var db : SQLiteDatabase? = null

    fun openDb() {
        db = dbBinHelper.writableDatabase
    }

    fun insertToDb(bin : String) {
        val values = ContentValues().apply {
            put(DbBinClass.COLUMN_BIN_NUMBER, bin)
        }
        db?.insert(DbBinClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readData() :ArrayList<String> {
        val dataList = ArrayList<String>()

        val cursor = db?.query(DbBinClass.TABLE_NAME, null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataText = cursor.getString(cursor.getColumnIndex(DbBinClass.COLUMN_BIN_NUMBER))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        dbBinHelper.close()
    }
}