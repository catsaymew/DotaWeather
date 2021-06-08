package com.example.dotaweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotaweather.R
import com.example.dotaweather.logic.model.LocationResponse
import com.example.dotaweather.logic.model.Weather
import com.example.dotaweather.logic.model.dailyWeatherList
import com.example.dotaweather.logic.model.hourlyWeatherList
import kotlinx.android.synthetic.main.daily_weather.*
import kotlinx.android.synthetic.main.hourly_weather.*
import kotlinx.android.synthetic.main.index_item.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.quality_item.*
import kotlinx.android.synthetic.main.soft_item.*
import kotlinx.android.synthetic.main.wind_item.*

private const val ARG_LOCATION = "param1"

class WeatherFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    private lateinit var hourlyAdapter: HourlyAdapter
    private lateinit var dailyAdapter: DailyAdapter
    private var location: LocationResponse.Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            location = it.getParcelable(ARG_LOCATION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)

        return rootView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            }else {
                Toast.makeText(activity, "没得到天气数据，为啥呢？", Toast.LENGTH_SHORT).show()

            }
        })
        location?.let { viewModel.refreshWeather(it.id) }
    }

    private fun showWeatherInfo(weather: Weather) {
        placeName.text = location?.name
        // 填写实时天气
        nowTemp.text = "${weather.now.temp}"
        val maxText = "${weather.daily[0].tempMax}°C"
        max.text = maxText
        val minText = "${weather.daily[0].tempMin}°C"
        min.text = minText
        nowSky.text = weather.now.text
        val airText = "空气${weather.quality.category}"
        airQuality.text = airText
        // 填写24小时天气
        val layoutManager =  LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        hourlyAdapter = HourlyAdapter(weather.hourly)
        recyclerView.adapter = hourlyAdapter
        // 填写15天天气
        val dailyLayoutManager = LinearLayoutManager(activity)
        dailyRV.layoutManager = dailyLayoutManager
        dailyAdapter = DailyAdapter(weather.daily)
        dailyRV.adapter = dailyAdapter
        // 填写空气质量
        airAqi.text = "${weather.quality.aqi}"
        airLevel.text = weather.quality.category
        pm10.text = "${weather.quality.pm10}"
        pm25.text = "${weather.quality.pm2p5}"
        no2.text = "${weather.quality.no2}"
        so2.text = "${weather.quality.so2}"
        o3.text = "${weather.quality.o3}"
        co.text = "${weather.quality.co}"
        // 填写生活指数
        val humidityText = "${weather.now.humidity}%"
        humidity.text = humidityText
        val feelsText = "${weather.now.feelsLike}°C"
        fellsTemp.text = feelsText
        val purpleText = "${weather.index[0].level}  ${weather.index[0].category}"
        purpleIndex.text = purpleText
        dress.text = weather.index[3].category
        play.text = weather.index[2].category
        cold.text = weather.index[4].category
        car.text = weather.index[1].category
        // 填写风
        windDir.text = weather.now.windDir
        val windText = "${weather.now.windScale}级风"
        windScale.text = windText
        // 填写日出日落月相
        val sunRise = weather.daily[0].sunrise
        val sunSet = weather.daily[0].sunset
        val moonPhase = weather.daily[0].moonPhase
    }

    companion object {
        @JvmStatic
        fun newInstance(location: LocationResponse.Location) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_LOCATION, location)
                }
            }
    }
}