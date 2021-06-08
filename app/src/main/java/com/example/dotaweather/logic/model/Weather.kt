package com.example.dotaweather.logic.model

data class Weather(
    val now: NowResponse.Now,
    val hourly: List<HourlyResponse.Hourly>,
    val daily: List<DailyResponse.Daily>,
    val index: List<IndexResponse.DailyIndex>,
    val quality: QualityResponse.Now
    )
