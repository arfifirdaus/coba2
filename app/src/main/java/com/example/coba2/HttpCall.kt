package com.example.coba2

class HttpCall {
    companion object {
        fun GET(): Int =1
        fun POST(): Int =0
    }

    var VURL:String=""
    var methodType: Int =0
    var params:HashMap<String, String> = HashMap<String, String>()
}