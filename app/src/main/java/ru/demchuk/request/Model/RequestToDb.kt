package ru.demchuk.request.Model

import android.content.Context
import ru.demchuk.request.VM.SelectListBin

class RequestToDb(val vm : SelectListBin, context: Context) {
    private val dbBinManager = DbBinManager(context)

    fun selectListBinInDb() {
        dbBinManager.openDb()
        val listBin = dbBinManager.readData()
        vm.sendListOnView(listBin)
        dbBinManager.closeDb()
    }
}