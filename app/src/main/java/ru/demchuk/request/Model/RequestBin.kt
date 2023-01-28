package ru.demchuk.request.Model


import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.codehaus.httpcache4j.uri.URIBuilder
import ru.demchuk.request.VM.BindBinWithURL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI


class RequestBin(bin: String, val vm: BindBinWithURL, context : Context) {
    private var bin: String
    private lateinit var uri: URI
    private val URL_START = "https://lookup.binlist.net/"
    private var dbManager = DbBinManager(context)

    init {
        this.bin = bin
        workWithDbInsert()
    }

    private fun workWithDbInsert() {
        dbManager.openDb()
        dbManager.insertToDb(bin)
        dbManager.closeDb()
    }
    fun buildRequest() {
        val urlFinite = URL_START + bin
        val urlBuilder = URIBuilder.fromString(urlFinite)
        uri = urlBuilder.toURI()
        GlobalScope.launch {
            getStringInOpenStream()
        }
    }

    private fun getStringInOpenStream() {
        val stringBuilder = java.lang.StringBuilder()
        try {
            println(uri)
            val bufferReader = BufferedReader(InputStreamReader(uri.toURL().openStream()))
            var inputLine: String?
            inputLine = bufferReader.readLine()
            while (inputLine?.isNotEmpty() == true) {
                stringBuilder.append(inputLine)
                inputLine = bufferReader.readLine()
            }
            bufferReader.close()
        } catch (error: Exception) {
            error.printStackTrace()
        } finally {
            vm.bindRequestWithView(stringBuilder.toString())
        }
    }


}