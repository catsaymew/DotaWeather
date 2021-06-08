package com.example.dotaweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class DotaWeatherApplication : Application() { // 用于获取全局Context，并提供令牌

    companion object {
        const val KEY = "c623431f853e49739fa1fb06877f2795" // 令牌
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}