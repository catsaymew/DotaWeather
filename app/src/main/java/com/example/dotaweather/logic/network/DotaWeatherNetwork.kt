package com.example.dotaweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object DotaWeatherNetwork {

    private val weatherService = ServiceCreator.create<WeatherService>() // 创建接口代理对象

    suspend fun getNowWeather(location: String) =
        weatherService.getNowWeather(location).await()

    suspend fun getHourlyWeather(location: String) =
        weatherService.getHourlyWeather(location).await()

    suspend fun getDailyWeather(location: String) =
        weatherService.getDailyWeather(location).await()

    private val indexService = ServiceCreator.create<IndexService>()

    suspend fun getIndex(location: String) = indexService.getIndex(location).await()
    suspend fun getQuality(location: String) = indexService.getQuality(location).await()

    private val locationService = LocationServiceCreator.create<LocationService>()
    suspend fun getLocation(location: String) = locationService.getLocation(location).await()

    private suspend fun <T> Call<T>.await(): T { // 返回一个对应类型的Response
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body() // 请求内容
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t) // 报出异常原因
                }

            })
        }
    }
}