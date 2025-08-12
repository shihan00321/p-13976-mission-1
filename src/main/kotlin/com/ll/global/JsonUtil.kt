package com.ll.global

import com.ll.WiseSaying

object JsonUtil {
    fun toJson(wiseSaying: WiseSaying): String {
        return "{\n" +
                "\t\"id\": ${wiseSaying.id},\n" +
                "\t\"content\": \"${wiseSaying.quote}\",\n" +
                "\t\"author\": \"${wiseSaying.author}\"\n" +
                "}"
    }


    fun fromJson(json: String): WiseSaying {
        val lines = json.lines().map { it.trim() }
        var id = 0L
        var content = ""
        var author = ""

        for (line in lines) {
            if (line.startsWith("\"id\"")) {
                id = line.split(":")[1].trim().trimEnd(',').toLong()
            } else if (line.startsWith("\"content\"")) {
                content = line.split(":")[1].trim().trim(',', '\"')
            } else if (line.startsWith("\"author\"")) {
                author = line.split(":")[1].trim().trim(',', '\"')
            }
        }

        return WiseSaying(id, content, author)
    }
}