package com.ll.wiseSaying

import com.ll.WiseSaying

class WiseSayingRepository {
    private val wiseSayings = mutableListOf<WiseSaying>()
    private var lastId = 0L

    fun save(wiseSaying: WiseSaying): WiseSaying {
        wiseSayings.add(wiseSaying)
        return wiseSaying
    }

    fun findAll(): List<WiseSaying> {
        return wiseSayings.toList()
    }

    fun findById(id: Long): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }

    fun delete(wiseSaying: WiseSaying): Boolean {
        return wiseSayings.remove(wiseSaying)
    }

    fun getNextId(): Long {
        return ++lastId
    }
}