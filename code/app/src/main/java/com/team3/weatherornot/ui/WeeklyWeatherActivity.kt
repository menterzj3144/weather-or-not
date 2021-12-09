package com.team3.weatherornot.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.navigation.BottomMenuNavigation
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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.weekly_nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_weekly
        bottomNavigationView.setOnItemSelectedListener {
            BottomMenuNavigation().onNavigationItemSelected(it, this)
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
}