package ru.demchuk.request.View

import android.app.Activity
import android.os.Bundle
import ru.demchuk.request.R

class ListBIN : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_bin)
    }

    override fun onStart() {
        super.onStart()
    }
}