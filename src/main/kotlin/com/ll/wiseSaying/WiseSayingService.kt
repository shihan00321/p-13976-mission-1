package com.ll.wiseSaying

import com.ll.WiseSaying
import com.ll.global.BeanConfig.wiseSayingRepository

class WiseSayingService{

    fun addWiseSaying(quote: String, author: String): WiseSaying {
        val id = wiseSayingRepository.getNextId()
        val wiseSaying = WiseSaying(id, quote, author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun findWiseSayingList(keywordType: String? = null, keyword: String? = null, page: Int = 1): PageResult {
        return wiseSayingRepository.findAll(keywordType, keyword, page)
    }

    fun deleteWiseSaying(id: Long): Boolean {
        val wiseSaying = wiseSayingRepository.findById(id)
        return if (wiseSaying != null) {
            wiseSayingRepository.delete(wiseSaying)
        } else {
            false
        }
    }

    fun updateWiseSaying(id: Long, newQuote: String, newAuthor: String): Boolean {
        return wiseSayingRepository.update(id, newQuote, newAuthor)
    }

    fun findById(id: Long): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun build() {
        wiseSayingRepository.build()
    }
}