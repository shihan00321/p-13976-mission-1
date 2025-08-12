package com.ll

class Rq(input : String) {
    private val command : String
    private val paramMap = mutableMapOf<String, String>()

    init {
        val split = input.split("?")
        command = split[0].trim()
        if (split.size > 1) {
            val params =  split[1].split("&")

            params.forEach { param ->
                val keyValue = param.split("=")
                if (keyValue.size == 2) {
                    paramMap[keyValue[0].trim()] = keyValue[1].trim()
                }
            }
        }
    }

    fun getCommand(): String = command

    fun getParam(id : String) : String? {
        return paramMap[id];
    }
}