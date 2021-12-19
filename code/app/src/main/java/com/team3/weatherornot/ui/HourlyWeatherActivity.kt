package com.team3.weatherornot.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.weather.Weather
import com.team3.weatherornot.location.MyLocationManager
import com.team3.weatherornot.navigation.BottomMenuNavigation

/**
 * The activity for the hourly weather view
 *
 * @constructor Create empty constructor for hourly weather activity
 */
class HourlyWeatherActivity : AppCompatActivity() {
    private val locationManager: MyLocationManager = MyLocationManager(this, ::getWeatherForLocation)

    /**
     * Creates the activity
     *
     * @param savedInstanceState Saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.hourly_weather)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.hourly_nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_hourly
        bottomNavigationView.setOnItemSelectedListener {
            BottomMenuNavigation().onNavigationItemSelected(it, this)
        }

        locationManager.requestPermissions()
    }

    /**
     * Function to be used as a callback to call the API after getting location
     *
     * @param lat Lat the latitude coordinate of the current location
     * @param lon Lon the longitude coordinate of the current location
     */
    private fun getWeatherForLocation(lat: Double, lon: Double) {
        APIManager.getInstance()!!.getWeatherForLocation(lat, lon, ::populateTextViews)
    }

    /**
     * Executes when the user either chooses to accept or deny permissions
     *
     * @param requestCode the request code of the permission
     * @param permissions the permissions being requested
     * @param grantResults the permissions granted
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //handle location permission acceptance/denial
        locationManager.onRequestPermissionsResult(requestCode, grantResults)
    }

    /**
     * Take the passed in weather information and display it on the app
     *
     * @param weather the weather information to be displayed
     */
    private fun populateTextViews(weather: Weather) {

        val today = weather.getHourlyWeatherForHours(12)

        val updateTimeTV = findViewById<TextView>(R.id.update_time_tv)
        updateTimeTV.text = ("Last updated: " + weather.updateTime)

        for ((i, hour) in today.withIndex()) {
            val tempId = resources.getIdentifier("temp$i", "id", packageName)
            val timeId = resources.getIdentifier("time$i", "id", packageName)
            val imgId = resources.getIdentifier("gridImg$i", "id", packageName)
            val hourImg = resources.getIdentifier("icon_" + hour.weatherImgId, "drawable", packageName)

            val tempTV = findViewById<TextView>(tempId)
            val timeTV = findViewById<TextView>(timeId)
            val imgView = findViewById<ImageView>(imgId)

            imgView.setImageResource(hourImg)
            tempTV.text = (hour.temp.toString() + getString(R.string.degreesF))
            timeTV.text = hour.getFormattedHour()
        }
    }
}

