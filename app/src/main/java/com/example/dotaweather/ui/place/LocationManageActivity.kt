package com.example.dotaweather.ui.place

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotaweather.R
import com.example.dotaweather.logic.Repository
import com.example.dotaweather.logic.model.LocationResponse
import kotlinx.android.synthetic.main.activity_location_manage.*

class LocationManageActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(LocationManageViewModel::class.java) }
    private lateinit var adapter: ManageAdapter

    private var locationList = ArrayList<LocationResponse.Location>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_manage)
        supportActionBar?.let {
            it.title = "管理城市"
            it.setDisplayHomeAsUpEnabled(true)
        } // 设置标题栏
        plusLocation.setOnClickListener {
            val intent = Intent(this, LocationSearchActivity::class.java)
            startActivityForResult(intent, 1)
        } // 点击添加城市按钮，跳转至城市搜索界面
        if (Repository.isLocationSaved()) { // 读取存储城市列表信息
            locationList = Repository.getSavedLocation() // 返回一个ArrayList<String>
//            for (location in locationList) {
//                // 待修改！！
//                val view = LayoutInflater.from(this).inflate(R.layout.text_item, locationLayout, false)
//                val locationTV: TextView = view.findViewById(R.id.locationTV)
//                locationTV.text = location
//                locationLayout.addView(view)
//            }
            val layoutManager = LinearLayoutManager(this)
            manageRV.layoutManager = layoutManager
            adapter = ManageAdapter(this, locationList)
            manageRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 将添加回来的结果新建一个条目
        // 这里返回的就只是一个String，把它也改成location对象
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val location = data?.getParcelableExtra<LocationResponse.Location>("location")

                if (location != null) {
                    locationList.add(location)
                    // 对列表进行修改，来进行数据的存储及传递
//                    adapter.notifyDataSetChanged()
                    val layoutManager = LinearLayoutManager(this)
                    manageRV.layoutManager = layoutManager
                    adapter = ManageAdapter(this, locationList)
                    manageRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                    Repository.saveLocation(locationList)

                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // 传递参数给上一个Activity
                val intent = Intent().apply {
                    putExtra("location", locationList)
                }
                setResult(RESULT_OK, intent)
//                Repository.saveLocation(locationList)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent().apply {
            putExtra("location", locationList)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}