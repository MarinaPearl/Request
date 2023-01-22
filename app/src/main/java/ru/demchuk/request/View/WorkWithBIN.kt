package ru.demchuk.request.View

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_work_with_bin.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.demchuk.request.Model.RequestBin
import ru.demchuk.request.R
import ru.demchuk.request.VM.BindBinWithURL

class WorkWithBIN : Activity() {

    private var bin : String? = null
    private var bindBinWithURL = BindBinWithURL()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_with_bin)
    }

    override fun onStart() {
        super.onStart()
//        setListView("asd")
        val observer = Observer<String>{
            runOnUiThread{
                var listBank = ArrayList<String>()
                listBank.add(it)
                println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  ${listBank.get(0)}")
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listBank)
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

    fun setListView(text : String) {
        var listBank = ArrayList<String>()
        listBank.add(text)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listBank)
        list.adapter = adapter
    }




}