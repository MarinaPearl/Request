package ru.demchuk.request

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_work_with_bin.*

class WorkWithBIN : Activity() {

    private var bin : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_with_bin)
    }

    override fun onStart() {
        super.onStart()
        editTextNumber.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    bin = editTextNumber.text.toString()
                    editTextNumber.clearFocus()
                    editTextNumber.isCursorVisible = false
                    return true
                }
                return false
            }
        })
    }




}