package com.example.dotaweather.ui.place

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaweather.R
import com.example.dotaweather.logic.Repository
import com.example.dotaweather.logic.model.LocationResponse

class ManageAdapter(private val context: Activity,
                    private val locationList: ArrayList<LocationResponse.Location>) :
    RecyclerView.Adapter<ManageAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val locationManageName: TextView = view.findViewById(R.id.locationManageName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manage_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnLongClickListener {
//            Toast.makeText(parent.context, "ok", Toast.LENGTH_SHORT).show()
            val position = holder.adapterPosition
            AlertDialog.Builder(context).apply {
                setTitle("咳咳")
                setMessage("是否删除该城市")
                setCancelable(false)
                setPositiveButton("确认") { dialog, which ->
                    locationList.removeAt(position)
                    Repository.saveLocation(locationList)
                    notifyDataSetChanged()
                }
                setNegativeButton("取消") { dialog, which ->
                }
                show()
            }
//            val place = locationList[position]
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locationList[position]
        holder.locationManageName.text = location.name
    }

    override fun getItemCount() = locationList.size
}