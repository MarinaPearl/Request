package ru.demchuk.request

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class ChoosingAction : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosing_action2)
    }

    fun onClickBin(view : View) {
        val intent = Intent(this, WorkWithBIN::class.java)
        startActivity(intent)
    }
}