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
        temp2.text = (today[1].getFormattedHour() + " " + today[1].temp.toString() + getString(R.string.degreesF))
        temp3.text = (today[2].getFormattedHour() + " " + today[2].temp.toString() + getString(R.string.degreesF))
        temp4.text = (today[3].getFormattedHour() + " " + today[3].temp.toString() + getString(R.string.degreesF))
        temp5.text = (today[4].getFormattedHour() + " " + today[4].temp.toString() + getString(R.string.degreesF))
        temp6.text = (today[5].getFormattedHour() + " " + today[5].temp.toString() + getString(R.string.degreesF))
        temp7.text = (today[6].getFormattedHour() + " " + today[6].temp.toString() + getString(R.string.degreesF))
        temp8.text = (today[7].getFormattedHour() + " " + today[7].temp.toString() + getString(R.string.degreesF))
        temp9.text = (today[8].getFormattedHour() + " " + today[8].temp.toString() + getString(R.string.degreesF))
    }
}

