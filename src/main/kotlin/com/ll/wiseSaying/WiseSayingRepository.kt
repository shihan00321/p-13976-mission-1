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

        if (fileDirectory.listFiles()?.none { it.name.endsWith(".json") && !it.name.equals("data.json") } == true) {
            for (i in 1..10) {
                val id = getNextId()
                val wiseSaying = WiseSaying(id, "명언 $i", "작자미상 $i")
                save(wiseSaying)
            }
        }
    }

    fun save(wiseSaying: WiseSaying): WiseSaying {
        val file = File(fileDirectory, "${wiseSaying.id}.json")
        val toJson = JsonUtil.toJson(wiseSaying)
        file.writeText(toJson)
        return wiseSaying
    }

    fun findAll(keywordType: String? = null, keyword: String? = null, page: Int = 1, pageSize: Int = 5): PageResult {
        val wiseSayings = mutableListOf<WiseSaying>()

        fileDirectory.listFiles()?.filter {
            it.name.endsWith(".json") && !it.name.equals("data.json")
        }?.forEach { file ->
            JsonUtil.fromJson(file.readText()).also { wiseSayings.add(it) }
        }

        val allWiseSayings = wiseSayings.sortedBy { it.id }.reversed()

        val filteredWiseSayings = if (keywordType != null && keyword != null) {
            allWiseSayings.filter { wiseSaying ->
                when (keywordType) {
                    "author" -> wiseSaying.author.contains(keyword)
                    "content" -> wiseSaying.quote.contains(keyword)
                    else -> false
                }
            }
        } else allWiseSayings

        val startIndex = (page - 1) * pageSize
        val endIndex = minOf(startIndex + pageSize, filteredWiseSayings.size)

        val paged = if (startIndex < filteredWiseSayings.size) {
            filteredWiseSayings.subList(startIndex, endIndex)
        } else emptyList()

        return PageResult(paged, filteredWiseSayings.size)
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

    fun build() {
        val file = File(fileDirectory, "data.json")
        val wiseSayings = findAll()

        val jsonList = wiseSayings.items.joinToString(
            separator = ",\n",
            prefix = "[\n",
            postfix = "\n]"
        ) { JsonUtil.toJson(it).prependIndent("\t") }

        file.writeText(jsonList)
    }

    fun clear() {
        fileDirectory.listFiles()?.forEach { it.delete() }
        lastId = 0L
    }

    private fun saveLastId() {
        lastIdFile.writeText(lastId.toString())
    }
}