package com

class App {
    fun run() {
        val wiseSayings = mutableListOf<WiseSaying>()
        var id = 1L

        println("== 명언 앱 ==")
        while (true) {
            print("명령) ")
            val input = readlnOrNull()?.trim() ?: continue
            val rq = Rq(input)
            when (rq.getCommand()) {
                "등록" -> {
                    print("명언 : ")
                    val quote = readln()
                    print("작가 : ")
                    val author = readln()
                    val wiseSaying = WiseSaying(id, quote, author)
                    wiseSayings.add(wiseSaying)
                    println("${id++}번 명언이 등록되었습니다.")
                }

                "목록" -> {
                    println("번호 / 작가 / 명언")
                    println("-------------------")
                    wiseSayings.reversed().forEach { println("${it.id} / ${it.author} / ${it.quote}") }
                }

                "삭제" -> {
                    val id = rq.getParam("id")!!.toLong()
                    val deleteWiseSaying = wiseSayings.find { it.id == id }
                    deleteWiseSaying ?: run {
                        println("${id}번 명언은 존재하지 않습니다.")
                        continue
                    }
                    wiseSayings.remove(deleteWiseSaying)
                    println("${id}번 명언이 삭제되었습니다.")
                }

                "수정" -> {
                    val id = rq.getParam("id")!!.toLong()
                    val updateWiseSaying = wiseSayings.find { it.id == id }
                    updateWiseSaying ?: run {
                        println("${id}번 명언은 존재하지 않습니다.")
                        continue
                    }

                    println("명언(기존): ${updateWiseSaying.quote}")
                    print("명언: ")
                    val newQuote = readln()
                    updateWiseSaying.quote = newQuote

                    println("작가(기존): ${updateWiseSaying.author}")
                    print("작가: ")
                    val newAuthor = readln()
                    updateWiseSaying.author = newAuthor

                }
                "종료" -> {
                    break
                }
                else -> println("알 수 없는 명령입니다.")
            }
        }
    }
}