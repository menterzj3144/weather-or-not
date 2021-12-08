package com.team3.weatherornot.ui

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

/**
 * The activity for the hourly weather view
 *
 * @constructor Create empty constructor for hourly weather activity
 */
class HourlyWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.hourly_weather)

        findViewById<BottomNavigationView>(R.id.hourly_nav_view).setOnItemSelectedListener {
            onNavigationItemSelected(it)
        }

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, ::populateTextViews)
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    private fun populateTextViews(weather: Weather) {
        val temp1 = findViewById<TextView>(R.id.textView)
        val temp2 = findViewById<TextView>(R.id.textView2)
        val temp3 = findViewById<TextView>(R.id.textView3)
        val temp4 = findViewById<TextView>(R.id.textView4)
        val temp5 = findViewById<TextView>(R.id.textView5)
        val temp6 = findViewById<TextView>(R.id.textView6)
        val temp7 = findViewById<TextView>(R.id.textView7)
        val temp8 = findViewById<TextView>(R.id.textView8)
        val temp9 = findViewById<TextView>(R.id.textView9)

        val today = weather.getHourlyWeatherForHours(9)

        temp1.text = (today[0].getFormattedHour() + " " + today[0].temp.toString() + getString(R.string.degreesF))
        temp2.text = (today[1].getFormattedHour() + " " + today[1].temp.toString() + getString(R.string.degreesF))
        temp3.text = (today[2].getFormattedHour() + " " + today[2].temp.toString() + getString(R.string.degreesF))
        temp4.text = (today[3].getFormattedHour() + " " + today[3].temp.toString() + getString(R.string.degreesF))
        temp5.text = (today[4].getFormattedHour() + " " + today[4].temp.toString() + getString(R.string.degreesF))
        temp6.text = (today[5].getFormattedHour() + " " + today[5].temp.toString() + getString(R.string.degreesF))
        temp7.text = (today[6].getFormattedHour() + " " + today[6].temp.toString() + getString(R.string.degreesF))
        temp8.text = (today[7].getFormattedHour() + " " + today[7].temp.toString() + getString(R.string.degreesF))
        temp9.text = (today[8].getFormattedHour() + " " + today[8].temp.toString() + getString(R.string.degreesF))
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