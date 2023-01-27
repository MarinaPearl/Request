package ru.demchuk.request.Model

import android.provider.BaseColumns

object DbBinClass {
    const val TABLE_NAME = "BIN"
    const val COLUMN_BIN_NUMBER = "number"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Request.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_BIN_NUMBER TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXITS $TABLE_NAME"
}