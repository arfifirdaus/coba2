package com.example.coba2

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import java.nio.charset.Charset

class JsonRequest<T> (
    url: String,
    method: Int,
    private val headers: MutableMap<String, String>?,
    private val listener: Response.Listener<String>,
    errorListener: Response.ErrorListener
) : Request<T>(method, url, errorListener) {
    override fun getHeaders(): MutableMap<String, String> = headers ?: super.getHeaders()

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val json = String(response?.data ?: ByteArray(0),
            Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
        //return Response.success(json)
        return null
    }

    override fun deliverResponse(response: T?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        listener.onResponse(response.toString())
    }
}