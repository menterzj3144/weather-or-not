package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the weekly weather view
 *
 * @constructor Create empty constructor for weekly weather activity
 */
class WeeklyWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.weekly_weather)

        findViewById<BottomNavigationView>(R.id.weekly_nav_view).setOnItemSelectedListener {
            onNavigationItemSelected(it)
        }

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, ::populateTextViews)
    }


    private fun populateTextViews(weather: Weather){
        var i = 1
        while (i < 8) {
            val imageViewId = resources.getIdentifier("day" + i + "_weather_img", "id", packageName)
            val minTempId = resources.getIdentifier("day" + i + "_mintemp", "id", packageName)
            val maxTempId = resources.getIdentifier("day" + i + "_maxtemp", "id", packageName)
            val precipTVId = resources.getIdentifier("day" + i + "_precip", "id", packageName)
            val dayNameId = resources.getIdentifier("day" + i + "_name", "id", packageName)

            val imageView = findViewById<ImageView>(imageViewId)
            val minTemp = findViewById<TextView>(minTempId)
            val maxTemp = findViewById<TextView>(maxTempId)
            val precipTV = findViewById<TextView>(precipTVId)
            val dayName = findViewById<TextView>(dayNameId)

            val day = weather.getSpecificDayWeather(i - 1)

            val resId = resources.getIdentifier("icon_" + day.weatherImgId, "drawable", packageName)
            imageView.setImageResource(resId)

            minTemp.text = (day.minTemp.toString() + "°F")
            maxTemp.text = (day.maxTemp.toString() + "°F")
            precipTV.text = (day.precip.toString() + "%")
            dayName.text = (day.getDayAbbreviation())

            i++
        }
    }

    /**
     * Switches to an activity based on which menu item was selected
     *
     * @param item the menu item that was selected
     * @return a success boolean
     */
    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_today -> {
                changeActivity(TodaysWeatherActivity())
            }
            R.id.navigation_hourly -> {
                changeActivity(HourlyWeatherActivity())
            }
            R.id.navigation_suggest_select -> {
                changeActivity(SuggestSelectActivity())
            }
        }
        return true
    }

    /**
     * Starts the specified activity
     *
     * @param activity the activity to be started
     */
    private fun changeActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

}