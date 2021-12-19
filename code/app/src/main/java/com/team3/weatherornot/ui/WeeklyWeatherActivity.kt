package com.team3.weatherornot.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.location.MyLocationManager
import com.team3.weatherornot.navigation.BottomMenuNavigation
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the weekly weather view
 *
 * @constructor Create empty constructor for weekly weather activity
 */
class WeeklyWeatherActivity : AppCompatActivity() {
    private val locationManager: MyLocationManager = MyLocationManager(this, ::getWeatherForLocation)

    /**
     * Creates the activity
     *
     * @param savedInstanceState Saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.weekly_weather)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.weekly_nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_weekly
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

            minTemp.text = (day.minTemp.toString() + getString(R.string.degreesF))
            maxTemp.text = (day.maxTemp.toString() + getString(R.string.degreesF))
            precipTV.text = (day.precip.toString() + "%")
            dayName.text = (day.getDayAbbreviation())

            i++
        }
    }
}