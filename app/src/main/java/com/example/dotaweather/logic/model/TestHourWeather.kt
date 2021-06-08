package com.example.dotaweather.logic.model

import com.example.dotaweather.R

class TestHourWeather(val hour: String, val hourlySky: Int, val houtlyTemp: String) {
}

val hourlyWeatherList = listOf<TestHourWeather>(
    TestHourWeather("10:00", R.drawable.s100, "23°C"),
    TestHourWeather("11:00", R.drawable.s101, "24°C"),
    TestHourWeather("12:00", R.drawable.s102, "23°C"),
    TestHourWeather("13:00", R.drawable.s103, "25°C"),
    TestHourWeather("14:00", R.drawable.s104, "23°C"),
    TestHourWeather("15:00", R.drawable.s150, "26°C"),
    TestHourWeather("16:00", R.drawable.s153, "23°C"),
    TestHourWeather("17:00", R.drawable.s154, "28°C"),
    TestHourWeather("18:00", R.drawable.s300, "23°C"),
    TestHourWeather("19:00", R.drawable.s301, "27°C"),
    TestHourWeather("20:00", R.drawable.s302, "23°C")
)

class TestDailyWeather(val date: String, val dailySky: Int, val dailyTempMax: Int, val dailyTempMin: Int) {}

val dailyWeatherList = listOf<TestDailyWeather>(
    TestDailyWeather("6.8", R.drawable.s100, 31, 15),
    TestDailyWeather("6.9", R.drawable.s101, 31, 15),
    TestDailyWeather("6.10", R.drawable.s102, 31, 15),
    TestDailyWeather("6.11", R.drawable.s103, 31, 15),
    TestDailyWeather("6.12", R.drawable.s104, 31, 15),
    TestDailyWeather("6.13", R.drawable.s150, 31, 15),
    TestDailyWeather("6.14", R.drawable.s153, 31, 15),
    TestDailyWeather("6.15", R.drawable.s154, 31, 15),
    TestDailyWeather("6.16", R.drawable.s300, 31, 15),
    TestDailyWeather("6.17", R.drawable.s301, 31, 15),
    TestDailyWeather("6.18", R.drawable.s302, 31, 15),
    TestDailyWeather("6.19", R.drawable.s303, 31, 15),
    TestDailyWeather("6.20", R.drawable.s304, 31, 15),
    TestDailyWeather("6.21", R.drawable.s305, 31, 15),
    TestDailyWeather("6.22", R.drawable.s306, 31, 15),
)
