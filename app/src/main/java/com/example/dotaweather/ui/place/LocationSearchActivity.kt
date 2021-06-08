package com.example.dotaweather.ui.place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotaweather.R
import kotlinx.android.synthetic.main.activity_location_search.*

class LocationSearchActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(LocationViewModel::class.java) }
    private lateinit var adapter: LocationAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)

        supportActionBar?.let {
            it.title = "添加城市"
            it.setDisplayHomeAsUpEnabled(true)
        }

        val layoutManager = LinearLayoutManager(this)
        locationRV.layoutManager = layoutManager
        adapter = LocationAdaptor(this, viewModel.locationList)
        locationRV.adapter = adapter

        editText.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.getLocation(content)
            } else {
                viewModel.locationList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.locationLiveData.observe(this, Observer { result ->
            val locations = result.getOrNull()
            if (locations != null) {
                viewModel.locationList.clear()
                viewModel.locationList.addAll(locations)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}