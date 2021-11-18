package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.R
import com.team3.weatherornot.api.WeatherAPIListener
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the today's weather view
 *
 * @constructor Create empty constructor for today's weather activity
 */
class TodaysWeatherActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.todays_weather)

        findViewById<NavigationBarView>(R.id.today_nav_view).setOnItemSelectedListener(this)

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, object :
            WeatherAPIListener<Weather> {
            override fun getResult(result: Weather) {
                populateTextViews(result)
            }
        })
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    private fun populateTextViews(weather: Weather) {
        val cityTV = findViewById<TextView>(R.id.current_location_name)
        val tempTV = findViewById<TextView>(R.id.current_weather_temp)
        val conditionTV = findViewById<TextView>(R.id.current_weather_condition)
        val precipTV = findViewById<TextView>(R.id.current_weather_precip)
        val morningTempTV = findViewById<TextView>(R.id.morning_weather_temp)
        val dayTempTV = findViewById<TextView>(R.id.day_weather_temp)
        val eveningTempTV = findViewById<TextView>(R.id.evening_weather_temp)
        val nightTempTV = findViewById<TextView>(R.id.night_weather_temp)
        val updateTimeTV = findViewById<TextView>(R.id.update_time_tv)
        val imageView = findViewById<ImageView>(R.id.current_weather_img)

        val current = weather.currentWeather
        val today = weather.getSpecificDayWeather(0)

        val resId = resources.getIdentifier("icon_" + current.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        cityTV.text = weather.getCityState(applicationContext)
        tempTV.text = (current.temp.toString() + getString(R.string.degreesF))
        conditionTV.text = current.condition
        precipTV.text = (current.precip.toString() + getString(R.string.percent))

        morningTempTV.text = (today.morning.toString() + getString(R.string.degreesF))
        dayTempTV.text = (today.day.toString() + getString(R.string.degreesF))
        eveningTempTV.text = (today.evening.toString() + getString(R.string.degreesF))
        nightTempTV.text = (today.night.toString() + getString(R.string.degreesF))
        updateTimeTV.append(weather.updateTime)
    }

    /**
     * Switches to an activity based on which menu item was selected
     *
     * @param item the menu item that was selected
     * @return a success boolean
     */
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