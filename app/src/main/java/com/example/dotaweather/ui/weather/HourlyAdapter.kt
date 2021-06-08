package com.example.dotaweather.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaweather.DotaWeatherApplication
import com.example.dotaweather.MainActivity
import com.example.dotaweather.R
import com.example.dotaweather.logic.model.HourlyResponse
import com.example.dotaweather.logic.model.TestHourWeather
import com.example.dotaweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter(private val hourlyWeatherList: List<HourlyResponse.Hourly>) :
    RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hour: TextView = view.findViewById(R.id.hour)
        val hourlySky: ImageView = view.findViewById(R.id.hourlySky)
        val hourlyTemp: TextView = view.findViewById(R.id.hourlyTemp)
        val hourlyRain: TextView = view.findViewById(R.id.rain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hourlyWeather = hourlyWeatherList[position]
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        holder.hour.text = simpleDateFormat.format(hourlyWeather.fxTime)
        holder.hourlySky.setImageResource(getSky(hourlyWeather.icon).icon)
        val rainText = "${hourlyWeather.pop}%"
        holder.hourlyRain.text = rainText
        if (hourlyWeather.pop == 0) { // 有待验证pop为空时的逻辑
            holder.hourlyRain.visibility = View.INVISIBLE
        }
        val tempText = "${hourlyWeather.temp}°C"
        holder.hourlyTemp.text = tempText
    }

    override fun getItemCount() = hourlyWeatherList.size
}