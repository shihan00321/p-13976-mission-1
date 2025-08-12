package com.ll

import com.ll.global.BeanConfig.wiseSayingController

class App {
    fun run() {

        println("== 명언 앱 ==")
        while (true) {
            print("명령) ")
            val input = readlnOrNull()?.trim() ?: continue
            val rq = Rq(input)
            when (rq.getCommand()) {
                "등록" -> wiseSayingController.addWiseSaying()
                "목록" -> wiseSayingController.showWiseSayings()
                "삭제" -> {
                    val id = rq.getParam("id")!!.toLong()
                    wiseSayingController.deleteWiseSaying(id)
                }
                "수정" -> {
                    val id = rq.getParam("id")!!.toLong()
                    wiseSayingController.updateWiseSaying(id)
                }
                "종료" -> break
                else -> println("알 수 없는 명령입니다.")
            }
        }
    }
}