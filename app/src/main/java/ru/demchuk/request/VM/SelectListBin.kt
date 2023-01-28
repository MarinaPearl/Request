package ru.demchuk.request.VM

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.demchuk.request.Model.RequestToDb

class SelectListBin(context : Context) {
    val liveData = MutableLiveData<ArrayList<String>>()
    val requestToDb = RequestToDb(this, context)

    fun selectList() {
        requestToDb.selectListBinInDb()
    }

    fun sendListOnView(listBin : ArrayList<String>) {
        liveData.postValue(listBin)
    }

}