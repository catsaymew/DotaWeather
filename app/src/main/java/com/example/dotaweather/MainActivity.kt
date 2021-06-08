package com.example.dotaweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotaweather.logic.Repository
import com.example.dotaweather.logic.model.LocationResponse
import com.example.dotaweather.logic.model.TestHourWeather
import com.example.dotaweather.logic.model.dailyWeatherList
import com.example.dotaweather.logic.model.hourlyWeatherList
import com.example.dotaweather.ui.place.LocationManageActivity
import com.example.dotaweather.ui.weather.DailyAdapter
import com.example.dotaweather.ui.weather.HourlyAdapter
import com.example.dotaweather.ui.weather.WeatherFragment
import com.example.dotaweather.ui.weather.WeatherFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.daily_weather.*
import kotlinx.android.synthetic.main.hourly_weather.*

class MainActivity : AppCompatActivity() {
    private var locationList = ArrayList<LocationResponse.Location>()
    private lateinit var adapter: WeatherFragmentPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        if (Repository.isLocationSaved()) {
            locationList = Repository.getSavedLocation()
        }
        initPager()
    }

    override fun onRestart() {
        super.onRestart()
        if (Repository.isLocationSaved()) {
            locationList = Repository.getSavedLocation()
        }
        initPager()
    }

    private fun initPager() {
        val fragmentList = mutableListOf<Fragment>()
        for (location in locationList) {
            fragmentList.add(WeatherFragment.newInstance(location))
        }

        adapter = WeatherFragmentPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.citymanage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.manage -> {
                val intent = Intent(this@MainActivity, LocationManageActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                locationList = data?.getParcelableArrayListExtra<LocationResponse.Location>("location") as ArrayList<LocationResponse.Location>
                initPager()
            }
        }
    }
}