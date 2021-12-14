package com.team3.weatherornot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.database.Dao
import com.team3.weatherornot.location.MyLocationManager
import com.team3.weatherornot.navigation.BottomMenuNavigation
import com.team3.weatherornot.navigation.ViewPagerAdapter
import com.team3.weatherornot.weather.Weather

/**
 * The activity for the suggest/select activity view
 *
 * @constructor Create empty constructor for suggest/select weather activity
 */
class SuggestSelectActivity : AppCompatActivity() {
    private val locationManager: MyLocationManager = MyLocationManager(this, ::getWeatherForLocation)

    /**
     * Creates the activity
     *
     * @param savedInstanceState Saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.suggest_select_layout)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.suggest_select_nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_suggest_select
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
        APIManager.getInstance()!!.getWeatherForLocation(lat, lon, ::setupFragments)
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
     * Initializes the fragments and the viewpager for this activity
     *
     * @param weather Weather the current weather information
     */
    private fun setupFragments(weather: Weather) {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        val weatherActivities = Dao.getJson(applicationContext)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SuggestFragment(weather, weatherActivities), SelectFragment(weather, weatherActivities)
        )

        viewPager.adapter = ViewPagerAdapter(fragments, this)
    }
}