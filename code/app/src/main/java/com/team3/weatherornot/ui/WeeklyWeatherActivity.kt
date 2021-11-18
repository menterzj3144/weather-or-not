package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.api.WeatherAPIListener
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the weekly weather view
 *
 * @constructor Create empty constructor for weekly weather activity
 */
class WeeklyWeatherActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.weekly_weather)

        findViewById<NavigationBarView>(R.id.weekly_nav_view).setOnItemSelectedListener(this)

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, object :
            WeatherAPIListener<Weather> {
            override fun getResult(result: Weather) {
                populateSun(result)
            }
        })
    }


    private fun populateSun(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.sunday_weather_img)
        val minTemp = findViewById<TextView>(R.id.sun_mintemp)
        val maxTemp = findViewById<TextView>(R.id.sun_maxtemp)
        val precipSun = findViewById<TextView>(R.id.sun_precip)

        //Fix day = 0 shows Today not Sunday
        val sunday0 = weather.getSpecificDayWeather(0)

        println(sunday0.weatherImgId)

        val resId = resources.getIdentifier("icon_" + sunday0.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (sunday0.minTemp.toString() + "°F")
        maxTemp.text = (sunday0.maxTemp.toString() + "°F")
        precipSun.text = (sunday0.precip.toString() + "%")
    }


    /**
     * Switches to an activity based on which menu item was selected
     *
     * @param item the menu item that was selected
     * @return a success boolean
     */
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
            R.id.navigation_suggest_select -> {
                changeActivity(SuggestSelectActivity())
                return true
            }
        }
        return false
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