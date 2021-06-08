package com.example.dotaweather.logic

import androidx.lifecycle.liveData
import com.example.dotaweather.logic.dao.LocationDao
import com.example.dotaweather.logic.model.LocationResponse
import com.example.dotaweather.logic.model.Weather
import com.example.dotaweather.logic.network.DotaWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun saveLocation(locationList: ArrayList<LocationResponse.Location>) = LocationDao.saveLocation(locationList)

    fun getSavedLocation() = LocationDao.getSavedLocation()

    fun isLocationSaved() = LocationDao.isLocationSaved()

    fun getLocation(location: String) = fire(Dispatchers.IO) {

        val locationResponse = DotaWeatherNetwork.getLocation(location)
        if (locationResponse.code == 200) {
            val locations = locationResponse.location
            Result.success(locations)
        } else {
            Result.failure(RuntimeException("response code is ${locationResponse.code}"))
        }

    }

    fun refreshWeather(location: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredNow = async {
                DotaWeatherNetwork.getNowWeather(location)
            }
            val deferredHourly = async {
                DotaWeatherNetwork.getHourlyWeather(location)
            }
            val deferredDaily = async {
                DotaWeatherNetwork.getDailyWeather(location)
            }
            val deferredIndex = async {
                DotaWeatherNetwork.getIndex(location)
            }
            val deferredQuality = async {
                DotaWeatherNetwork.getQuality(location)
            }
            val nowResponse = deferredNow.await()
            val hourlyResponse = deferredHourly.await()
            val dailyResponse = deferredDaily.await()
            val indexResponse = deferredIndex.await()
            val qualityResponse = deferredQuality.await()
            if (
                nowResponse.code == 200 &&
                hourlyResponse.code == 200 &&
                dailyResponse.code == 200 &&
                indexResponse.code == 200 &&
                qualityResponse.code == 200
            ) {
                val weather = Weather(
                    nowResponse.now,
                    hourlyResponse.hourly,
                    dailyResponse.daily,
                    indexResponse.daily,
                    qualityResponse.now
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "now response code is ${nowResponse.code}" +
                                "hourly response code is ${hourlyResponse.code}" +
                                "daily response code is ${dailyResponse.code}" +
                                "index response code is ${indexResponse.code}" +
                                "quality response code is ${qualityResponse.code}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}