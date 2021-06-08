package com.example.dotaweather.logic.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LocationResponse(val code: Int, val location: List<Location>) {

    @Parcelize
    data class Location(
        val name: String,
        val id: String,
        val lat: String,
        val lon: String,
        val adm2: String,
        val adm1: String,
        val country: String
    ) : Parcelable
}
