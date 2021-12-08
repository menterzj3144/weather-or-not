package com.team3.weatherornot.DatabaseFiles

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Dao {
    fun getJson(applicationContext: Context): List<WeatherActivity> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "db.json")
        if (jsonFileString != null) {
            Log.i("data", jsonFileString)
        }
        val gson = Gson()
        val listWeatherType = object : TypeToken<List<WeatherActivity>>() {}.type
        return gson.fromJson(jsonFileString, listWeatherType)
    }
}