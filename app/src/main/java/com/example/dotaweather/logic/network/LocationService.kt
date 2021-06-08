package com.example.dotaweather.logic.network

import com.example.dotaweather.DotaWeatherApplication
import com.example.dotaweather.logic.model.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("v2/city/lookup?key=${DotaWeatherApplication.KEY}")
    fun getLocation(@Query("location") location: String): Call<LocationResponse>
}