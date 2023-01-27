package ru.demchuk.request.View

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.Observer
import com.google.gson.Gson
import ru.demchuk.request.R
import ru.demchuk.request.VM.BindBinWithURL
import ru.demchuk.request.View.adapter.ListBINAdapter

class WorkWithBIN : Activity() {

    private var bin: String? = null
    private var bindBinWithURL = BindBinWithURL()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_with_bin)
    }

    override fun onStart() {
        super.onStart()
        val observer = Observer<String> {
            runOnUiThread {
                val gson = Gson()
                val bank = gson.fromJson(it, BIN::class.java)
                val listAbout = ArrayList<BIN>()
                val list = findViewById<ListView>(R.id.list)
                if (bank != null) {
                    listAbout.add(bank)
                    val adapter = ListBINAdapter(this, listAbout)
                    list.adapter = adapter
                } else {
                    var listNotFound = ArrayList<String>()
                    listNotFound.add("NOT FOUND")
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, listNotFound)
                    list.adapter = adapter
                }
            }
        }
        bindBinWithURL.liveData.observeForever(observer)
        var editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        editTextNumber.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    bin = editTextNumber.text.toString()
                    editTextNumber.clearFocus()
                    editTextNumber.isCursorVisible = false
                    bindBinWithURL.bindURL(bin!!)
                    return true
                }
                return false
            }
        })
    }
}