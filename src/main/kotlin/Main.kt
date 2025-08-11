package com

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("== 명언 앱 ==")
    while (true) {
        print("명령) ")
        val input = readlnOrNull()
        when(input) {
            "등록" -> {
                print("명언 : ")
                val quote = readlnOrNull()
                print("작가 : ")
                val author = readlnOrNull()
            }
            "종료" -> {
                break
            }
            else -> println("알 수 없는 명령입니다.")
        }
    }
}