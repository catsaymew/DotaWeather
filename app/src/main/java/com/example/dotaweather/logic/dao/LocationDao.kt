package com.example.dotaweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.dotaweather.DotaWeatherApplication
import com.example.dotaweather.logic.model.LocationResponse
import com.google.gson.Gson
import com.google.gson.JsonParser

object LocationDao {
    fun saveLocation(locationList: ArrayList<LocationResponse.Location>) {
        sharedPreferences().edit {
            putString("locationList", Gson().toJson(locationList))
        }
    }

    fun getSavedLocation(): ArrayList<LocationResponse.Location> {
        val locationJson = sharedPreferences().getString("locationList", "")
        val jsonArray = JsonParser().parse(locationJson).asJsonArray
        val locationList = ArrayList<LocationResponse.Location>()
        for (jsonElement in jsonArray) {
            locationList.add(Gson().fromJson(jsonElement, LocationResponse.Location::class.java))
        }
        return locationList
    }

    fun isLocationSaved() = sharedPreferences().contains("locationList")

    private fun sharedPreferences() = DotaWeatherApplication
        .context
        .getSharedPreferences("location_list", Context.MODE_PRIVATE)
}