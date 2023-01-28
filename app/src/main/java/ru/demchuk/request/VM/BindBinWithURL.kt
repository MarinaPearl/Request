package ru.demchuk.request.VM

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.demchuk.request.Model.RequestBin

class BindBinWithURL {

    val liveData = MutableLiveData<String>()

    fun bindURL(bin : String, context: Context) {
        val req = RequestBin(bin, this, context)
        req.buildRequest()
    }

    fun bindRequestWithView(aboutBank : String) {
        liveData.postValue(aboutBank)
    }
}