package com.example.dotaweather.logic.model

import java.util.*

data class DailyResponse(val code: Int, val daily: List<Daily>) {
    data class Daily(
        val fxDate: Date, // 日期
        val sunrise: String, // 日出时间，格式hh:mm
        val sunset: String, // 日落时间
        val moonrise: String, // 月出时间
        val moonset: String, // 月落时间
        val moonPhase: String, // 月相
        val tempMax: Int, // 最高温度
        val tempMin: Int, // 最低温度
        val iconDay: Int, // 白天天气图标代码
        val textDay: String, // 白天天气文字描述
        val iconNight: Int, // 夜间天气图标代码
        val textNight: String // 夜间天气文字描述
    )
}
