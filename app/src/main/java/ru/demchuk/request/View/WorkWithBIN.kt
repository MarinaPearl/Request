package ru.demchuk.request.View

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_work_with_bin.*
import kotlinx.android.synthetic.main.item_layoyt.*
import ru.demchuk.request.R
import ru.demchuk.request.VM.BindBinWithURL
import ru.demchuk.request.View.adapter.ListBINAdapter

class WorkWithBIN : Activity() {

    private var bin : String? = null
    private var bindBinWithURL = BindBinWithURL()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_with_bin)
    }

    override fun onStart() {
        super.onStart()
        val observer = Observer<String> {
            runOnUiThread{
                var gson = Gson()
                var bank = gson?.fromJson(it, BIN::class.java)
                var listAbout = ArrayList<BIN>()
                if (bank != null) {
                    listAbout.add(bank)
                }
                val adapter = ListBINAdapter(this, listAbout)
                list.adapter = adapter
            }
        }
        bindBinWithURL.liveData.observeForever(observer)
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