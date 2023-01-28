package ru.demchuk.request.View

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
                val listAbout = ArrayList<String>()
                val list = findViewById<ListView>(R.id.list)
                if (bank != null) {
                    addInformationToList(listAbout, bank)
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, listAbout)
                    list.adapter = adapter
                    listenListInformation(list, listAbout)
                } else {
                    val listNotFound = ArrayList<String>()
                    listNotFound.add("NOT FOUND")
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNotFound)
                    list.adapter = adapter
                }
            }
        }
        bindBinWithURL.liveData.observeForever(observer)
        listenEdittextBin()
    }

    private fun addInformationToList(listAbout: ArrayList<String>, bank: BIN) {
        listAbout.add("Length - ${bank.number.length}")
        listAbout.add("Luhn - ${bank.number.luhn}")
        listAbout.add("Scheme - ${bank.scheme}")
        listAbout.add("Type - ${bank.type}")
        listAbout.add("Brand - ${bank.brand}")
        listAbout.add("Prepaid - ${bank.prepaid}")
        listAbout.add("Numeric - ${bank.country.numeric}")
        listAbout.add("Alpha2 - ${bank.country.alpha2}")
        listAbout.add("Name - ${bank.country.name}")
        listAbout.add("Emoji - ${bank.country.emoji}")
        listAbout.add("Currency - ${bank.country.currency}")
        listAbout.add("Latitude - ${bank.country.latitude}")
        listAbout.add("Longitude - ${bank.country.longitude}")
        listAbout.add("Name's bank - ${bank.bank.name}")
        listAbout.add(bank.bank.url)
        listAbout.add(bank.bank.phone)
        listAbout.add("City - ${bank.bank.city}")
    }

    private fun listenEdittextBin() {
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val context = this
        editTextNumber.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    bin = editTextNumber.text.toString()
                    editTextNumber.clearFocus()
                    editTextNumber.isCursorVisible = false
                    bindBinWithURL.bindURL(bin!!, context)
                    return true
                }
                return false
            }
        })
    }

    private fun listenListInformation(list : ListView, listAbout : ArrayList<String>) {
        list.setOnItemClickListener { adapterView, view, i, l ->
            when (i) {
                15 -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    val phone = "tel:" + listAbout[i]
                    intent.data = Uri.parse(phone)
                    startActivity(intent)
                }
                14 -> {
                    var url = listAbout[i]
                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                        url = "http://$url"
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
                11, 12 -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    val latitude = listAbout[11].substringBefore("-")
                    val longitude = listAbout[12].substringBefore("-")
                    intent.data = Uri.parse("geo:$latitude,$longitude")
                    startActivity(intent)
                }
            }
        }
    }
}




