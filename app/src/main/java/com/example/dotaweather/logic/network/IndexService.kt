package com.example.dotaweather.logic.network

import com.example.dotaweather.DotaWeatherApplication
import com.example.dotaweather.logic.model.IndexResponse
import com.example.dotaweather.logic.model.QualityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IndexService {

    @GET("v7/indices/1d?type=1,2,3,5,9&key=${DotaWeatherApplication.KEY}")
    fun getIndex(@Query("location") location: String):
            Call<IndexResponse>

    @GET("v7/air/now?key=${DotaWeatherApplication.KEY}")
    fun getQuality(@Query("location") location: String):
            Call<QualityResponse>
}