package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.team3.weatherornot.ui.ViewPagerAdapter
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.R

class SuggestSelectActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.suggest_select_layout)

        findViewById<NavigationBarView>(R.id.suggest_select_nav_view).setOnItemSelectedListener(this)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SuggestFragment(), SelectFragment()
        )

        val adapter2 = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter2
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_today -> {
                changeActivity(TodaysWeatherActivity())
                return true
            }
            R.id.navigation_hourly -> {
                changeActivity(HourlyWeatherActivity())
                return true
            }
            R.id.navigation_weekly -> {
                changeActivity(WeeklyWeatherActivity())
                return true
            }
        }
        return false
    }

    private fun changeActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}