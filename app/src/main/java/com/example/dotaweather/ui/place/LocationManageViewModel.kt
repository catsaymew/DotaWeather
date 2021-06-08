package com.example.dotaweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dotaweather.logic.Repository
import com.example.dotaweather.logic.model.LocationResponse

class LocationManageViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val locationList = ArrayList<LocationResponse.Location>()

    val locationLiveData = Transformations.switchMap(searchLiveData) { location ->
        Repository.getLocation(location)
    }

    fun getLocation(location: String) {
        searchLiveData.value = location
    }

    fun saveLocation(locationList: ArrayList<LocationResponse.Location>) = Repository.saveLocation(locationList)

    fun getSavedLocation() = Repository.getSavedLocation()

    fun isLocationSaved() = Repository.isLocationSaved()

}