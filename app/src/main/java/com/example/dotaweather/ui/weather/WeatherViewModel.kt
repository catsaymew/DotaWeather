package com.example.dotaweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dotaweather.logic.Repository

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<String>()

    var locationId = ""

    var locationName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location)
    }

    fun refreshWeather(location: String) {
        locationLiveData.value = location
    }
}