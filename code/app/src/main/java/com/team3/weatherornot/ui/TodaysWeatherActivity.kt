package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.R
import com.team3.weatherornot.api.WeatherAPIListener
import com.team3.weatherornot.weather.CurrentWeather
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

        val api = APIManager.getInstance()
        val weather = api!!.getWeatherForLocation(44.811348, -91.498497)
        if (weather != null) {
            populateTextViews(weather)
        } else {
            api.getWeatherForLocationAPI(44.811348, -91.498497, object :
                WeatherAPIListener<Weather> {
                override fun getResult(result: Weather) {
                    populateTextViews(result)
                }
            })
        }
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    private fun populateTextViews(weather: Weather) {
        val tempTV = findViewById<TextView>(R.id.current_weather_temp)
        val conditionTV = findViewById<TextView>(R.id.current_weather_condition)
        val precipTV = findViewById<TextView>(R.id.current_weather_precip)
        val morningTempTV = findViewById<TextView>(R.id.morning_weather_temp)
        val morningPrecipTV = findViewById<TextView>(R.id.morning_weather_precip)
        val afternoonTempTV = findViewById<TextView>(R.id.afternoon_weather_temp)
        val afternoonPrecipTV = findViewById<TextView>(R.id.afternoon_weather_precip)
        val eveningTempTV = findViewById<TextView>(R.id.evening_weather_temp)
        val eveningPrecipTV = findViewById<TextView>(R.id.evening_weather_precip)
        val nightTempTV = findViewById<TextView>(R.id.night_weather_temp)
        val nightPrecipTV = findViewById<TextView>(R.id.night_weather_precip)

        val current = weather.currentWeather
        println(current.time.toString())
        tempTV.text = (current.temp.toString() + getString(R.string.degreesF))
        conditionTV.text = current.condition
        precipTV.text = (current.precip.toString() + getString(R.string.percent))
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