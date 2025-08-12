package com.ll.wiseSaying

import com.ll.WiseSaying

data class PageResult(
    val items: List<WiseSaying>,
    val totalCount: Int
)