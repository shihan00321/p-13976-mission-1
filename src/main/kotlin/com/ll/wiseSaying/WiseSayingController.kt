package com.ll.wiseSaying

import com.ll.global.BeanConfig.wiseSayingService

class WiseSayingController{

    fun addWiseSaying() {
        print("명언 : ")
        val quote = readln()
        print("작가 : ")
        val author = readln()

        val wiseSaying = wiseSayingService.addWiseSaying(quote, author)
        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun showWiseSayings(keywordType : String? = null, keyword: String? = null) {
        if (keywordType != null && keyword != null) {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }
        val wiseSayings = wiseSayingService.findWiseSayingList(keywordType, keyword)
        println("번호 / 작가 / 명언")
        println("----------------------")
        wiseSayings.forEach { wiseSaying ->
            println("${wiseSaying.id} / ${wiseSaying.author} / ${wiseSaying.quote}")
        }
    }

    fun deleteWiseSaying(id: Long) {
        wiseSayingService.findById(id)?.let {
            wiseSayingService.deleteWiseSaying(id)
            println("${id}번 명언이 삭제되었습니다.")
        } ?: println("${id}번 명언은 존재하지 않습니다.")
    }

    fun updateWiseSaying(id: Long) {
        wiseSayingService.findById(id)?.let {
            println("명언(기존): ${it.quote}")
            print("명언: ")
            val newQuote = readln()

            println("작가(기존): ${it.author}")
            print("작가: ")
            val newAuthor = readln()

            wiseSayingService.updateWiseSaying(id, newQuote, newAuthor)
            println("${id}번 명언이 수정되었습니다.")
        } ?: println("${id}번 명언은 존재하지 않습니다.")
    }

    fun build() {
        wiseSayingService.build()
        println("data.json 파일의 내용이 갱신되었습니다.")
    }
}