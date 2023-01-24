package ru.demchuk.request.VM

import androidx.lifecycle.MutableLiveData
import ru.demchuk.request.Model.RequestBin
import ru.demchuk.request.View.WorkWithBIN

class BindBinWithURL {

    val liveData = MutableLiveData<String>()


    fun bindURL(bin : String) {
        val req = RequestBin(bin, this)
        req.buildRequest()
    }

    fun bindRequestWithView(aboutBank : String) {
        liveData.postValue(aboutBank)
    }
}