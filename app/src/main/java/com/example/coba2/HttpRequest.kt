package com.example.coba2

import android.os.AsyncTask
import java.io.*
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

public class HttpRequest : AsyncTask<HttpCall, String, String>() {

    override fun doInBackground(vararg params: HttpCall?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val urlConnection: HttpURLConnection
        val httpCall = params[0]
        val response: StringBuilder = StringBuilder()

        try {
            val dataParams: String = getDataString(httpCall!!.params, httpCall!!.methodType)
            var url: URL = URL(if(httpCall.methodType == HttpCall.GET()){httpCall.VURL +dataParams} else {httpCall.VURL})
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = if(httpCall.methodType == HttpCall.GET()) "GET" else "POST"
            if(httpCall.params != null && httpCall.methodType == HttpCall.POST())
            {
                var os:OutputStream = urlConnection.outputStream
                var writer:BufferedWriter = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                writer.append(dataParams)
                writer.flush()
                writer.close()
                os.close()
            }

            val responseCode: Int = urlConnection.responseCode
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                var line: String
                var br:BufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                for(line in br.readLine()){
                    response.append(line)
                }
            }
        } catch (e: UnsupportedEncodingException)
        {
            e.printStackTrace()
            response.append(e.toString())
        }

        return response.toString()
    }


    private fun getDataString(params: HashMap<String, String>, methodType: Int) : String
    {
        val result: StringBuilder = StringBuilder()
        var isFirst = true
        for (entry: Map.Entry<String, String> in params)
        {
            if(isFirst)
            {
                isFirst=false
                if(methodType == HttpCall.GET())
                {
                    result.append("?")
                }
            }else
            {
                result.append("&")
            }

            result.append(URLEncoder.encode(entry.key),"UTF-8")
            result.append("=")
            result.append(URLEncoder.encode(entry.value), "UTF-8")
        }

        return result.toString()
    }

}