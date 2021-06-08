package com.example.dotaweather.ui.place

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaweather.R
import com.example.dotaweather.logic.model.LocationResponse

class LocationAdaptor(private val context: Activity,
                      private val locationList: List<LocationResponse.Location>) :
    RecyclerView.Adapter<LocationAdaptor.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val locationName: TextView = view.findViewById(R.id.locationName)
        val locationAddress: TextView = view.findViewById(R.id.locationAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val location = locationList[position]
            val intent = Intent().apply {
                putExtra("location", location)
            }
            context.setResult(Activity.RESULT_OK, intent)
            context.finish()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locationList[position]
        holder.locationName.text = location.name
        val addressText = "${location.adm1},${location.adm2},${location.country}"
        holder.locationAddress.text = addressText
    }

    override fun getItemCount() = locationList.size

}