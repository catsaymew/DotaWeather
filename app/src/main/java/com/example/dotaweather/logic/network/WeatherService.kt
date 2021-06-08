package com.example.dotaweather.logic.network

import com.example.dotaweather.DotaWeatherApplication
import com.example.dotaweather.logic.model.DailyResponse
import com.example.dotaweather.logic.model.HourlyResponse
import com.example.dotaweather.logic.model.IndexResponse
import com.example.dotaweather.logic.model.NowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v7/weather/now?key=${DotaWeatherApplication.KEY}")
    fun getNowWeather(@Query("location") location: String):
            Call<NowResponse>

    @GET("v7/weather/24h?key=${DotaWeatherApplication.KEY}")
    fun getHourlyWeather(@Query("location") location: String):
            Call<HourlyResponse>

    @GET("v7/weather/7d?key=${DotaWeatherApplication.KEY}")
    fun getDailyWeather(@Query("location") location: String):
            Call<DailyResponse>
}