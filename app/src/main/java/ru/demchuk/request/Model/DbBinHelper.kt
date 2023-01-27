package ru.demchuk.request.Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbBinHelper(context: Context) : SQLiteOpenHelper(context, DbBinClass.DATABASE_NAME,
null, DbBinClass.DATABASE_VERSION){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(DbBinClass.CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DbBinClass.SQL_DELETE_TABLE)
        onCreate(p0)
    }

}