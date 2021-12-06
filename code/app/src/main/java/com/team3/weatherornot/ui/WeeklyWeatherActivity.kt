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
import com.team3.weatherornot.api.WeatherAPIListener
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

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, object :
            WeatherAPIListener<Weather> {
            override fun getResult(result: Weather) {
                populateFirstRow(result)
                populateSecondRow(result)
                populateThirdRow(result)
                populateFourthRow(result)
                populateFifthRow(result)
                populateSixthRow(result)
                populateSeventhRow(result)
            }
        })
    }


    private fun populateFirstRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day1_weather_img)
        val minTemp = findViewById<TextView>(R.id.day1_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day1_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day1_precip)
        val day1Name = findViewById<TextView>(R.id.day1_name)

        val today_1 = weather.getSpecificDayWeather(0)

        val resId = resources.getIdentifier("icon_" + today_1.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_1.minTemp.toString() + "°F")
        maxTemp.text = (today_1.maxTemp.toString() + "°F")
        precipTV.text = (today_1.precip.toString() + "%")
        day1Name.text = (today_1.getDayAbbreviation())
    }

    private fun populateSecondRow(weather:Weather){
        val imageView = findViewById<ImageView>(R.id.day2_weather_img)
        val minTemp = findViewById<TextView>(R.id.day2_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day2_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day2_precip)
        val day1Name = findViewById<TextView>(R.id.day2_name)

        val today_2 = weather.getSpecificDayWeather(1)


        val resId = resources.getIdentifier("icon_" + today_2.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_2.minTemp.toString() + "°F")
        maxTemp.text = (today_2.maxTemp.toString() + "°F")
        precipTV.text = (today_2.precip.toString() + "%")
        day1Name.text = (today_2.getDayAbbreviation())
    }

    private fun populateThirdRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day3_weather_img)
        val minTemp = findViewById<TextView>(R.id.day3_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day3_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day3_precip)
        val day1Name = findViewById<TextView>(R.id.day3_name)

        val today_3 = weather.getSpecificDayWeather(2)

        val resId = resources.getIdentifier("icon_" + today_3.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_3.minTemp.toString() + "°F")
        maxTemp.text = (today_3.maxTemp.toString() + "°F")
        precipTV.text = (today_3.precip.toString() + "%")
        day1Name.text = (today_3.getDayAbbreviation())
    }

    private fun populateFourthRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day4_weather_img)
        val minTemp = findViewById<TextView>(R.id.day4_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day4_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day4_precip)
        val day1Name = findViewById<TextView>(R.id.day4_name)

        val today_4 = weather.getSpecificDayWeather(3)

        val resId = resources.getIdentifier("icon_" + today_4.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_4.minTemp.toString() + "°F")
        maxTemp.text = (today_4.maxTemp.toString() + "°F")
        precipTV.text = (today_4.precip.toString() + "%")
        day1Name.text = (today_4.getDayAbbreviation())
    }

    private fun populateFifthRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day5_weather_img)
        val minTemp = findViewById<TextView>(R.id.day5_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day5_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day5_precip)
        val day1Name = findViewById<TextView>(R.id.day5_name)

        val today_5 = weather.getSpecificDayWeather(4)

        val resId = resources.getIdentifier("icon_" + today_5.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_5.minTemp.toString() + "°F")
        maxTemp.text = (today_5.maxTemp.toString() + "°F")
        precipTV.text = (today_5.precip.toString() + "%")
        day1Name.text = (today_5.getDayAbbreviation())
    }

    private fun populateSixthRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day6_weather_img)
        val minTemp = findViewById<TextView>(R.id.day6_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day6_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day6_precip)
        val day1Name = findViewById<TextView>(R.id.day6_name)

        val today_6 = weather.getSpecificDayWeather(5)

        val resId = resources.getIdentifier("icon_" + today_6.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_6.minTemp.toString() + "°F")
        maxTemp.text = (today_6.maxTemp.toString() + "°F")
        precipTV.text = (today_6.precip.toString() + "%")
        day1Name.text = (today_6.getDayAbbreviation())
    }

    private fun populateSeventhRow(weather: Weather){
        val imageView = findViewById<ImageView>(R.id.day7_weather_img)
        val minTemp = findViewById<TextView>(R.id.day7_mintemp)
        val maxTemp = findViewById<TextView>(R.id.day7_maxtemp)
        val precipTV = findViewById<TextView>(R.id.day7_precip)
        val day1Name = findViewById<TextView>(R.id.day7_name)

        val today_7 = weather.getSpecificDayWeather(6)


        val resId = resources.getIdentifier("icon_" + today_7.weatherImgId, "drawable", packageName)
        imageView.setImageResource(resId)

        minTemp.text = (today_7.minTemp.toString() + "°F")
        maxTemp.text = (today_7.maxTemp.toString() + "°F")
        precipTV.text = (today_7.precip.toString() + "%")
        day1Name.text = (today_7.getDayAbbreviation())
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