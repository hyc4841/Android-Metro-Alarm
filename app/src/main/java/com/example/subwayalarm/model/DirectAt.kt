package com.example.finalproject

object DirectAt {
    val directAt = mutableMapOf<Int, String>(
        1 to "급행", 0 to "아님", 7 to "특급"
    )

    fun SearchDirect(d: Int): String? {
        return directAt[d]
    }
}