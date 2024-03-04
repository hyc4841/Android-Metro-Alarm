package com.example.finalproject

object LineList {
    val lineList = mutableMapOf<Int, String>(
        1001 to "1호선", 1002 to "2호선", 1003 to "3호선", 1004 to "4호선", 1005 to "5호선",
        1006 to "6호선", 1007 to "7호선", 1008 to "8호선", 1009 to "9호선", 1063 to "경의중앙선",
        1065 to "공항철도", 1067 to "경춘선", 1075 to "수인분당선", 1077 to "신분당선", 1081 to "경강선",
        1092 to "우이신설선", 1093 to "서해선"
        )

    fun SearchLine(line: Int): String? {
        return lineList[line]
    }
}