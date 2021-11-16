package com.team3.weatherornot.weather

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

/**
 * Contains all of the necessary weather information from an API call
 *
 * @property lat the latitude coordinate of the location
 * @property lon the longitude coordinate of the location
 * @constructor Create [Weather]
 *
 * @param json the JSON object to be converted to a Weather object
 */
@RequiresApi(Build.VERSION_CODES.O)
class Weather(val lat: Double, val lon: Double, json: JSONObject) {
    var currentWeather: CurrentWeather = CurrentWeather(Date(), -500, -1, "")
    val weeklyWeather: ArrayList<DailyWeather> = ArrayList()
    val hourlyWeather: ArrayList<CurrentWeather> = ArrayList()
    var timezone: String = ""

    init {
        timezone = json.getString("timezone")
        setCurrentWeather(json.getJSONObject("current"), json.getJSONArray("hourly").getJSONObject(0))
        setHourlyWeather(json.getJSONArray("hourly"))
        setDailyWeather(json.getJSONArray("daily"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertTimeToDate(time: Long): Date {
        val instant = Instant.ofEpochSecond(time)
        return Date.from(instant)
    }

    /**
     * Initializes the current weather value with the correct values from the JSON object
     *
     * @param current the current weather portion of the JSON string
     * @param hour the current hour portion of the JSON string
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentWeather(current: JSONObject, hour: JSONObject) {
        val dt = convertTimeToDate(current.getLong("dt"))
        val temp = current.getInt("temp")
        val precip = hour.getInt("pop")
        val condition = current.getJSONArray("weather").getJSONObject(0).getString("main")

        currentWeather = CurrentWeather(dt, temp, precip, condition)
    }

    /**
     * Creates weather objects for each hour of the next 48 hours and adds them to the hourlyWeather list
     *
     * @param hourly the hourly weather JSON array
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setHourlyWeather(hourly: JSONArray) {
        var i = 0
        while (i < 48) {
            val hour = hourly.getJSONObject(i)
            val dt = convertTimeToDate(hour.getLong("dt"))
            val temp = hour.getInt("temp")
            val precip = hour.getInt("pop")
            val condition = hour.getJSONArray("weather").getJSONObject(0).getString("main")

            hourlyWeather.add(CurrentWeather(dt, temp, precip, condition))

            i++
        }
    }

    /**
     * Creates weather objects for each day of the next 7 days and adds them to the weeklyWeather list
     *
     * @param daily the daily weather JSON array
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDailyWeather(daily: JSONArray) {
        var i = 0
        while (i < 7) {
            val day = daily.getJSONObject(i)
            val dt = convertTimeToDate(day.getLong("dt"))
            val temp = day.getJSONObject("temp")
            val minTemp = temp.getInt("min")
            val maxTemp = temp.getInt("max")
            val precip = day.getInt("pop")
            val condition = day.getJSONArray("weather").getJSONObject(0).getString("main")

            weeklyWeather.add(DailyWeather(dt, minTemp, maxTemp, precip, condition))

            i++
        }
    }

    /**
     * Finds and returns the weather information for a specified day of the week
     *
     * @param day the day of the week to get the weather information for
     * @return [DailyWeather] the weather information for the specified day
     */
    fun getSpecificDayWeather(day: Int): DailyWeather {
        return weeklyWeather[day]
    }

    /**
     * Finds and returns the weather information for a specified number of hours
     *
     * @param hours the number of hours
     * @return a list of weather objects for each hour
     */
    fun getHourlyWeatherForHours(hours: Int): ArrayList<CurrentWeather> {
        return hourlyWeather
    }

}
