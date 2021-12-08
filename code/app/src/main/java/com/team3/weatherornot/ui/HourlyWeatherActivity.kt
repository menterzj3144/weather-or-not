package com.team3.weatherornot.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.weather.Weather
import com.team3.weatherornot.database.Dao

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
            Navigation().onNavigationItemSelected(it, this)
        }
        
        // Use this line to get the db activities and put them in a list.
        // applicationContext varies per activity so make sure to just copy the whole line with the dao.getJson(applicationContext) 
        // exactly as it is.
        val weatherActivities = Dao.getJson(applicationContext)
        // Example printing out the activity description of the 6th activity in the list
//        println(weatherActivities[6].activity_Desc)

        APIManager.getInstance()!!.getWeatherForLocation(44.8113, -91.4985, ::populateTextViews)
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    @SuppressLint("Range")
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
        temp2.text = (today[0].getFormattedHour() + " " + today[1].temp.toString() + getString(R.string.degreesF))
        temp3.text = (today[0].getFormattedHour() + " " + today[2].temp.toString() + getString(R.string.degreesF))
        temp4.text = (today[0].getFormattedHour() + " " + today[3].temp.toString() + getString(R.string.degreesF))
        temp5.text = (today[0].getFormattedHour() + " " + today[4].temp.toString() + getString(R.string.degreesF))
        temp6.text = (today[0].getFormattedHour() + " " + today[5].temp.toString() + getString(R.string.degreesF))
        temp7.text = (today[0].getFormattedHour() + " " + today[6].temp.toString() + getString(R.string.degreesF))
        temp8.text = (today[0].getFormattedHour() + " " + today[7].temp.toString() + getString(R.string.degreesF))
        temp9.text = (today[0].getFormattedHour() + " " + today[8].temp.toString() + getString(R.string.degreesF))
    }
}

