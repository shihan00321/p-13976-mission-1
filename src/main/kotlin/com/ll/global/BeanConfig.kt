package com.ll.global

import com.ll.wiseSaying.WiseSayingController
import com.ll.wiseSaying.WiseSayingRepository
import com.ll.wiseSaying.WiseSayingService

object BeanConfig {
    val wiseSayingController : WiseSayingController by lazy {
        WiseSayingController()
    }

    val wiseSayingService : WiseSayingService by lazy {
        WiseSayingService()
    }

    val wiseSayingRepository : WiseSayingRepository by lazy {
        WiseSayingRepository()
    }
}