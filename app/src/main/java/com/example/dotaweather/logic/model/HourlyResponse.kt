package com.example.dotaweather.logic.model

import java.util.*

data class HourlyResponse(val code: Int, val hourly: List<Hourly>) {
    data class Hourly(
        val fxTime: Date, // 时间
        val temp: Int, // 温度
        val icon: Int, // 天气图标代码
        val text: String, // 天气文字描述
        val pop: Int // 降水概率，百分比值，单位%
    )
}
