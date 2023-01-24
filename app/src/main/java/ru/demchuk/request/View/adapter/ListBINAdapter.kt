package ru.demchuk.request.View.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.demchuk.request.R
import ru.demchuk.request.View.BIN

class ListBINAdapter(context: Context, list: ArrayList<BIN>) : BaseAdapter() {

    private var listBins = ArrayList<BIN>()
    private var layoutInflater: LayoutInflater

    init {
        listBins = list
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return listBins.size
    }

    override fun getItem(p0: Int): Any {
        return listBins[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0 as Long
    }

    @SuppressLint("SetTextI18n")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1
        if (p1 == null) {
            view = layoutInflater.inflate(R.layout.item_layoyt, p2, false)
        }
        var binAbout = getBIN(p0)
        var textView2 = view?.findViewById(R.id.textView2) as TextView
        var textView3 = view?.findViewById(R.id.textView3) as TextView
        var textView4 = view?.findViewById(R.id.textView4) as TextView
        var textView5 = view?.findViewById(R.id.textView5) as TextView
        var textView6 = view?.findViewById(R.id.textView6) as TextView
        var textView7 = view?.findViewById(R.id.textView7) as TextView
        var textView8 = view?.findViewById(R.id.textView8) as TextView
        var textView9 = view?.findViewById(R.id.textView9) as TextView
        var textView10 = view?.findViewById(R.id.textView10) as TextView
        var textView11 = view?.findViewById(R.id.textView11) as TextView
        var textView12 = view?.findViewById(R.id.textView12) as TextView
        var textView13 = view?.findViewById(R.id.textView13) as TextView
        var textView14 = view?.findViewById(R.id.textView14) as TextView
        var textView15 = view?.findViewById(R.id.textView15) as TextView
        var textView16 = view?.findViewById(R.id.textView16) as TextView
        var textView17 = view?.findViewById(R.id.textView17) as TextView
        var textView18 = view?.findViewById(R.id.textView18) as TextView
        textView2.setText("Length - ${binAbout.number.length}")
        textView3.setText("Luhn - ${binAbout.number.luhn}")
        textView4.setText("Scheme - ${binAbout.scheme}")
        textView5.setText("Type - ${binAbout.type}")
        textView6.setText("Brand - ${binAbout.brand}")
        textView7.setText("Prepaid - ${binAbout.prepaid}")
        textView8.setText("Numeric - ${binAbout.country.numeric}")
        textView9.setText("Alpha2 - ${binAbout.country.alpha2}")
        textView10.setText("Name - ${binAbout.country.name}")
        textView11.setText("Emoji - ${binAbout.country.emoji}")
        textView12.setText("Currency - ${binAbout.country.currency}")
        textView13.setText("Latitude - ${binAbout.country.latitude}")
        textView14.setText("Longitude - ${binAbout.country.longitude}")
        textView15.setText("Name's bank - ${binAbout.bank.name}")
        textView16.setText("URL - ${binAbout.bank.url}")
        textView17.setText("Phone - ${binAbout.bank.phone}")
        textView18.setText("City - ${binAbout.bank.city}")

        return view
    }

    private fun getBIN(pos: Int): BIN {
        return getItem(pos) as BIN
    }
}