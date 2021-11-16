package com.team3.weatherornot.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.team3.weatherornot.R

/**
 * The activity for the hourly weather view
 *
 * @constructor Create empty constructor for hourly weather activity
 */
class HourlyWeatherActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.hourly_weather)

        findViewById<NavigationBarView>(R.id.hourly_nav_view).setOnItemSelectedListener(this)
    }

    /**
     * Switches to an activity based on which menu item was selected
     *
     * @param item the menu item that was selected
     * @return a success boolean
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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