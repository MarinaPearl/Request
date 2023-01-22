package ru.demchuk.request.Model

import android.util.JsonReader
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.codehaus.httpcache4j.uri.URIBuilder
import org.json.JSONObject
import ru.demchuk.request.VM.BindBinWithURL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI


class RequestBin(bin: String, val vm: BindBinWithURL) {
    private var bin: String
    private lateinit var uri: URI
    private val URL_START = "https://lookup.binlist.net/"

    init {
        this.bin = bin
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
        try {
            var stringBuilder = java.lang.StringBuilder()
            println(uri)
            var bufferReader = BufferedReader(InputStreamReader(uri.toURL().openStream()))
            var inputLine: String?
            inputLine = bufferReader?.readLine()
            while (inputLine?.isNotEmpty() == true) {
                stringBuilder.append(inputLine)
                println(inputLine)
                inputLine = bufferReader?.readLine()
            }
            bufferReader?.close()
            var gson = Gson()
            var bank = gson?.fromJson(stringBuilder.toString(), BIN.BIN::class.java)

            if (bank != null) {
                vm.bindRequestWithView(bank)
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }


}