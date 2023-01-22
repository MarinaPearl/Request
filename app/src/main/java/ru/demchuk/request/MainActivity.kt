package ru.demchuk.request

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStart(view : View) {
        val intent = Intent(this, ChoosingAction::class.java)
        startActivity(intent)
    }
}