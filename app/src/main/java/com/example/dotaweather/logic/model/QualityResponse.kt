package com.example.dotaweather.logic.model

data class QualityResponse(val code: Int, val now: Now) {
    data class Now(
        val aqi: Int, // 空气质量指数
        val level: Int, // 空气质量指数等级
        val category: String, // 空气质量指数级别
        val pm10: Int, // PM10
        val pm2p5: Int, // PM2.5
        val no2: Int, // 二氧化氮
        val so2: Int, // 二氧化硫
        val co: Float, // 一氧化碳
        val o3: Int // 臭氧
    )
}
