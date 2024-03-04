package com.example.finalproject

object Upd {
    val upd = mutableMapOf<Int, String>(
        0 to "상행/내선", 1 to "하행/외선"
    )
    fun Updn(u: Int): String? {
        return upd[u]
    }
}