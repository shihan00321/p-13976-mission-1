package com.ll.wiseSaying

import com.ll.WiseSaying
import com.ll.global.JsonUtil
import java.io.File

class WiseSayingRepository {
    private val fileDirectory = File("db/wiseSaying")
    private val lastIdFile = File(fileDirectory, "lastId.txt")
    private var lastId = 0L

    init {
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs()
        }
        if (lastIdFile.exists()) {
            lastId = lastIdFile.readText().toLong()
        } else {
            lastIdFile.writeText(lastId.toString())
        }
    }

    fun save(wiseSaying: WiseSaying): WiseSaying {
        val file = File(fileDirectory, "${wiseSaying.id}.json")
        val toJson = JsonUtil.toJson(wiseSaying)
        file.writeText(toJson)
        return wiseSaying
    }

    fun findAll(): List<WiseSaying> {
        val wiseSayings = mutableListOf<WiseSaying>()

        fileDirectory.listFiles()?.filter { it.name.endsWith(".json") }?.forEach { file ->
            JsonUtil.fromJson(file.readText()).let { wiseSayings.add(it) }
        }
        return wiseSayings.sortedBy { it.id }.reversed()
    }

    fun findById(id: Long): WiseSaying? {
        val file = File(fileDirectory, "$id.json")
        if (!file.exists()) {
            return null
        }
        val jsonContent = file.readText()
        val wiseSaying = JsonUtil.fromJson(jsonContent)
        return wiseSaying
    }

    fun delete(wiseSaying: WiseSaying): Boolean {
        val file = File(fileDirectory, "${wiseSaying.id}.json")
        return file.delete()
    }

    fun update(id: Long, content: String, author: String): Boolean {
        return findById(id)?.let {
            val updatedWiseSaying = WiseSaying(id, content, author)
            save(updatedWiseSaying)
            true
        } ?: false
    }

    fun getNextId(): Long {
        lastId++
        saveLastId()
        return lastId
    }

    private fun saveLastId() {
        lastIdFile.writeText(lastId.toString())
    }
}