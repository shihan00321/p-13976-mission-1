package com
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val wiseSayings = mutableListOf<WiseSaying>()
    var id = 1L

    println("== 명언 앱 ==")
    while (true) {
        print("명령) ")
        val input = readlnOrNull()
        when(input) {
            "등록" -> {
                print("명언 : ")
                val quote = readln()
                print("작가 : ")
                val author = readln()
                val wiseSaying = WiseSaying(id, quote, author)
                wiseSayings.add(wiseSaying)
                println("${id++}번 명언이 등록되었습니다.")
            }
            "종료" -> {
                break
            }
            else -> println("알 수 없는 명령입니다.")
        }
    }
}