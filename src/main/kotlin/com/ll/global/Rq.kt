package com.ll

class Rq(input : String) {
    private val command : String
    private val paramMap = mutableMapOf<String, String>()

    init {
        val split = input.split("?")
        command = split[0].trim()
        if (split.size > 1) {
            val params = split[1].split("=")
            paramMap[params[0].trim()] = params[1].trim()
        }
    }

    fun getCommand(): String = command

    fun getParam(id : String) : String? {
        return paramMap[id];
    }
}