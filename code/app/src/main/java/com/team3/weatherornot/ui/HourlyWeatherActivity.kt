package com.team3.weatherornot.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
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

        val today = weather.getHourlyWeatherForHours(12)

        val updateTimeTV = findViewById<TextView>(R.id.update_time_tv)
        updateTimeTV.text = ("Last updated: " + weather.updateTime)

        val temp1 = findViewById<TextView>(R.id.temp)
        val temp2 = findViewById<TextView>(R.id.temp1)
        val temp3 = findViewById<TextView>(R.id.temp2)
        val temp4 = findViewById<TextView>(R.id.temp3)
        val temp5 = findViewById<TextView>(R.id.temp4)
        val temp6 = findViewById<TextView>(R.id.temp5)
        val temp7 = findViewById<TextView>(R.id.temp6)
        val temp8 = findViewById<TextView>(R.id.temp7)
        val temp9 = findViewById<TextView>(R.id.temp8)

        val time1 = findViewById<TextView>(R.id.time)
        val time2 = findViewById<TextView>(R.id.time1)
        val time3 = findViewById<TextView>(R.id.time2)
        val time4 = findViewById<TextView>(R.id.time3)
        val time5 = findViewById<TextView>(R.id.time4)
        val time6 = findViewById<TextView>(R.id.time5)
        val time7 = findViewById<TextView>(R.id.time6)
        val time8 = findViewById<TextView>(R.id.time7)
        val time9 = findViewById<TextView>(R.id.time8)

        val img1 = findViewById<ImageView>(R.id.gridImg)
        val img2 = findViewById<ImageView>(R.id.gridImg1)
        val img3 = findViewById<ImageView>(R.id.gridImg2)
        val img4 = findViewById<ImageView>(R.id.gridImg3)
        val img5 = findViewById<ImageView>(R.id.gridImg4)
        val img6 = findViewById<ImageView>(R.id.gridImg5)
        val img7 = findViewById<ImageView>(R.id.gridImg6)
        val img8 = findViewById<ImageView>(R.id.gridImg7)
        val img9 = findViewById<ImageView>(R.id.gridImg8)

        val tempImg1 = resources.getIdentifier("icon_" + today[0].weatherImgId, "drawable", packageName)
        val tempImg2 = resources.getIdentifier("icon_" + today[1].weatherImgId, "drawable", packageName)
        val tempImg3 = resources.getIdentifier("icon_" + today[2].weatherImgId, "drawable", packageName)
        val tempImg4 = resources.getIdentifier("icon_" + today[3].weatherImgId, "drawable", packageName)
        val tempImg5 = resources.getIdentifier("icon_" + today[4].weatherImgId, "drawable", packageName)
        val tempImg6 = resources.getIdentifier("icon_" + today[5].weatherImgId, "drawable", packageName)
        val tempImg7 = resources.getIdentifier("icon_" + today[6].weatherImgId, "drawable", packageName)
        val tempImg8 = resources.getIdentifier("icon_" + today[7].weatherImgId, "drawable", packageName)
        val tempImg9 = resources.getIdentifier("icon_" + today[8].weatherImgId, "drawable", packageName)

        img1.setImageResource(tempImg1)
        img2.setImageResource(tempImg2)
        img3.setImageResource(tempImg3)
        img4.setImageResource(tempImg4)
        img5.setImageResource(tempImg5)
        img6.setImageResource(tempImg6)
        img7.setImageResource(tempImg7)
        img8.setImageResource(tempImg8)
        img9.setImageResource(tempImg9)

        time1.text = today[0].getFormattedHour()
        temp1.text = today[0].temp.toString() + getString(R.string.degreesF)
        img1.setImageResource(tempImg1)

        time2.text = today[1].getFormattedHour()
        temp2.text = today[1].temp.toString() + getString(R.string.degreesF)
        img2.setImageResource(tempImg2)

        time3.text = today[2].getFormattedHour()
        temp3.text = today[2].temp.toString() + getString(R.string.degreesF)
        img3.setImageResource(tempImg3)

        time4.text = today[3].getFormattedHour()
        temp4.text = today[3].temp.toString() + getString(R.string.degreesF)
        img4.setImageResource(tempImg4)

        time5.text = today[4].getFormattedHour()
        temp5.text = today[4].temp.toString() + getString(R.string.degreesF)
        img5.setImageResource(tempImg5)

        time6.text = today[5].getFormattedHour()
        temp6.text = today[5].temp.toString() + getString(R.string.degreesF)
        img6.setImageResource(tempImg6)

        time7.text = today[6].getFormattedHour()
        temp7.text = today[6].temp.toString() + getString(R.string.degreesF)
        img7.setImageResource(tempImg7)

        time8.text = today[7].getFormattedHour()
        temp8.text = today[7].temp.toString() + getString(R.string.degreesF)
        img8.setImageResource(tempImg8)

        time9.text = today[8].getFormattedHour()
        temp9.text = today[8].temp.toString() + getString(R.string.degreesF)
        img9.setImageResource(tempImg9)
    }
}

