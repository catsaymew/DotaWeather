package com.example.dotaweather.logic.model

data class NowResponse(val code: Int, val now: Now) {
    data class Now(
        val temp: Int, // 温度
        val feelsLike: Int, // 体感温度
        val icon: Int, // 天气图标代码
        val text: String, // 天气文字描述
        val vis: Int, // 能见度
        val windDir: String, // 风向
        val windScale: Int, // 风力等级
        val windSpeed: Int, // 风速
        val humidity: Int, // 相对湿度，百分比
    )
}
