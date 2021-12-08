package com.team3.weatherornot.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.weather.Weather
import com.team3.weatherornot.DatabaseFiles.Dao
import java.util.*

/**
 * The activity for the hourly weather view
 *
 * @constructor Create empty constructor for hourly weather activity
 */
class HourlyWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.hourly_weather)

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, object :
            WeatherAPIListener<Weather> {
            fun getResult(result: Weather) {
                // Use this line to get the db activities and put them in a list
                // applicationContext varies per activity so make sure to just copy the whole line with the dao.getJson(applicationContext) exactly as it is
                var weatherActivities = Dao.getJson(applicationContext)
                // Example printing out the activity description of the 6th activity in the list
                println(weatherActivities[6].Activity_Desc.toString())
                populateTextViews(result)
            }
        })
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    @SuppressLint("Range")
    private fun populateTextViews(weather: Weather) {
        findViewById<BottomNavigationView>(R.id.hourly_nav_view).setOnItemSelectedListener(this)
        val temp1 = findViewById<TextView>(R.id.textView)
        val temp2 = findViewById<TextView>(R.id.textView2)
        val temp3 = findViewById<TextView>(R.id.textView3)
        val temp4 = findViewById<TextView>(R.id.textView4)
        val temp5 = findViewById<TextView>(R.id.textView5)
        val temp6 = findViewById<TextView>(R.id.textView6)
        val temp7 = findViewById<TextView>(R.id.textView7)
        val temp8 = findViewById<TextView>(R.id.textView8)
        val temp9 = findViewById<TextView>(R.id.textView9)

        val currentWeather = weather.currentWeather
        val today = weather.getHourlyWeatherForHours(9)
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date

        temp1.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY)) + amPm(cal.get(Calendar.HOUR_OF_DAY)) + " " + today[0].temp.toString() + getString(R.string.degreesF))
        temp2.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 1) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 1) + " " + today[1].temp.toString() + getString(R.string.degreesF))
        temp3.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 2) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 2) + " " + today[2].temp.toString() + getString(R.string.degreesF))
        temp4.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 3) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 3) + " " + today[3].temp.toString() + getString(R.string.degreesF))
        temp5.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 4) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 4) + " " + today[4].temp.toString() + getString(R.string.degreesF))
        temp6.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 5) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 5) + " " + today[5].temp.toString() + getString(R.string.degreesF))
        temp7.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 6) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 6) + " " + today[6].temp.toString() + getString(R.string.degreesF))
        temp8.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 7) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 7) + " " + today[7].temp.toString() + getString(R.string.degreesF))
        temp9.text = (calcHour(cal.get(Calendar.HOUR_OF_DAY) + 8) + amPm(cal.get(Calendar.HOUR_OF_DAY) + 8) + " " + today[8].temp.toString() + getString(R.string.degreesF))
    }

    private fun calcHour(hour: Int): String {
        if(hour <= 12) {
            return hour.toString()
        } else {
            val tempHour = (hour - 12)
            if(tempHour > 12) {
                return (tempHour - 12).toString()
            } else {
                return tempHour.toString()
            }
        }
    }

    private fun amPm(hour: Int): String {
        if(hour <= 12) {
            return "am"
        } else if(hour <= 24) {
            return "pm"
        } else {
            return "am"
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