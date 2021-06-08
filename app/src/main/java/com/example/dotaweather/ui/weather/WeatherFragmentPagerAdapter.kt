package com.example.dotaweather.ui.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class WeatherFragmentPagerAdapter(private val fragmentManager: FragmentManager,
                                  private val lifecycle: Lifecycle,
                                  private val fragmentList: MutableList<Fragment>) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]

}