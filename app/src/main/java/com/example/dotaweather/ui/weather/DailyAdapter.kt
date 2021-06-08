package com.example.dotaweather.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaweather.R
import com.example.dotaweather.logic.model.DailyResponse
import com.example.dotaweather.logic.model.TestDailyWeather
import com.example.dotaweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter(private val dailyWeatherList: List<DailyResponse.Daily>) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.dailyDate)
        val dailySky: ImageView = view.findViewById(R.id.dailySky)
        val dailyTemp: TextView = view.findViewById(R.id.dailyTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dailyWeather = dailyWeatherList[position]
        val simpleDateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
        holder.day.text = simpleDateFormat.format(dailyWeather.fxDate)
        holder.dailySky.setImageResource(getSky(dailyWeather.iconDay).icon)
        val tempText = "${dailyWeather.tempMax}°C / ${dailyWeather.tempMin}°C"
        holder.dailyTemp.text = tempText
    }

    override fun getItemCount() = dailyWeatherList.size
}