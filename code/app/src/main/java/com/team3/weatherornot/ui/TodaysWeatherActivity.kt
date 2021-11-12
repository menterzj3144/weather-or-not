package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.api.Weather
import com.team3.weatherornot.myapplication.R
import org.json.JSONObject

class TodaysWeatherActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.todays_weather)

        findViewById<NavigationBarView>(R.id.today_nav_view).setOnItemSelectedListener(this)

        val api = Weather(this)
        val lambda = { json: JSONObject -> println(json.toString()) }
        api.executeRequest(44.811348, -91.498497, lambda)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_hourly -> {
                changeActivity(HourlyWeatherActivity())
                return true
            }
            R.id.navigation_weekly -> {
                changeActivity(WeeklyWeatherActivity())
                return true
            }
            R.id.navigation_suggest_select -> {
                changeActivity(SuggestSelectActivity())
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