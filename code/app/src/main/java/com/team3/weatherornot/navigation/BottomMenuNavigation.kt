package com.team3.weatherornot.navigation

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.team3.weatherornot.R
import com.team3.weatherornot.ui.HourlyWeatherActivity
import com.team3.weatherornot.ui.SuggestSelectActivity
import com.team3.weatherornot.ui.TodaysWeatherActivity
import com.team3.weatherornot.ui.WeeklyWeatherActivity

/**
 * Bottom navigation menu class
 *
 * @constructor Create empty constructor for bottom menu navigation
 */
class BottomMenuNavigation {
    /**
     * Switches to an activity based on which menu item was selected
     *
     * @param item the menu item that was selected
     * @return a success boolean
     */
    fun onNavigationItemSelected(item: MenuItem, context: Activity): Boolean {
        when (item.itemId) {
            R.id.navigation_today -> {
                changeActivity(TodaysWeatherActivity(), context)
            }
            R.id.navigation_hourly -> {
                changeActivity(HourlyWeatherActivity(), context)
            }
            R.id.navigation_weekly -> {
                changeActivity(WeeklyWeatherActivity(), context)
            }
            R.id.navigation_suggest_select -> {
                changeActivity(SuggestSelectActivity(), context)
            }
        }
        return true
    }

    /**
     * Starts the specified activity
     *
     * @param activity the activity to be started
     */
    private fun changeActivity(activity: Activity, context: Activity) {
        val intent = Intent(context, activity::class.java)
        context.startActivity(intent)
    }
}