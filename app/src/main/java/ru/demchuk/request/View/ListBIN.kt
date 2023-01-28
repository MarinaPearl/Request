package ru.demchuk.request.View

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import ru.demchuk.request.R
import ru.demchuk.request.VM.SelectListBin

class ListBIN : Activity() {

    private val vm = SelectListBin(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_bin)
    }

    override fun onStart() {
        super.onStart()
        vm.selectList()
        val observer = Observer<ArrayList<String>> {
            val listBin = findViewById<ListView>(R.id.listBin)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
            listBin.adapter = adapter
        }
        vm.liveData.observeForever(observer)
    }


}