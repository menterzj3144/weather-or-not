package com.team3.weatherornot.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.R
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the today's weather view
 *
 * @constructor Create empty constructor for today's weather activity
 */
class TodaysWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.todays_weather)

        findViewById<BottomNavigationView>(R.id.today_nav_view).setOnItemSelectedListener {
            Navigation().onNavigationItemSelected(it, this)
        }

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, ::populateTextViews)
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
        val dayTempTV = findViewById<TextView>(R.id.day_weather_temp)
        val eveningTempTV = findViewById<TextView>(R.id.evening_weather_temp)
        val nightTempTV = findViewById<TextView>(R.id.night_weather_temp)
        val updateTimeTV = findViewById<TextView>(R.id.update_time_tv)
        val imageView = findViewById<ImageView>(R.id.current_weather_img)

        val current = weather.currentWeather
        val today = weather.getSpecificDayWeather(0)

        val resId = resources.getIdentifier("icon_" + current.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        weather.getCityState(applicationContext, ::setCityStateText)
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
     * Callback function to set the city state text when the geocoder thread finishes
     *
     * @param cityState the city and state to display
     */
    private fun setCityStateText(cityState: String) {
        val cityTV = findViewById<TextView>(R.id.current_location_name)
        cityTV.text = cityState
    }
}