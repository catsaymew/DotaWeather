package com.example.dotaweather.logic.model

data class IndexResponse(val code: Int, val daily: List<DailyIndex>) {
    data class DailyIndex(
        val type: Int, // 类型
        val name: String, // 指数名
        val level: Int, // 指数等级
        val category: String, // 指数分类
        val text: String // 建议
    )
}
