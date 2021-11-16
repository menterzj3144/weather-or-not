package com.team3.weatherornot

import android.app.Application
import com.team3.weatherornot.api.APIManager

/**
 * Runs on app startup and instantiates the APIManager for the app
 */
class WeatherOrNotApp : Application() {
    override fun onCreate() {
        super.onCreate()
        println("App Created")
        APIManager.instantiate(this)
    }
}