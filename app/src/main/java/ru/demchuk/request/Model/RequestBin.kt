package ru.demchuk.request.Model


import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.codehaus.httpcache4j.uri.URIBuilder
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
        var stringBuilder = java.lang.StringBuilder()
        try {
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
        } catch (error: Exception) {
            error.printStackTrace()
        } finally {
            vm.bindRequestWithView(stringBuilder.toString())
        }
    }


}