package com.team3.weatherornot.database

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Dao {
    fun getJson(applicationContext: Context): List<WeatherActivity> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "db.json")
        val gson = Gson()
        val listWeatherType = object : TypeToken<List<WeatherActivity>>() {}.type
        return gson.fromJson(jsonFileString, listWeatherType)
    }
}