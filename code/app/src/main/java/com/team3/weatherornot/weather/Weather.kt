package com.team3.weatherornot.weather

import org.json.JSONArray
import org.json.JSONObject

/**
 * Contains all of the necessary weather information from an API call
 *
 * @property lat the latitude coordinate of the location
 * @property lon the longitude coordinate of the location
 * @constructor Create [Weather]
 *
 * @param json the JSON object to be converted to a Weather object
 */
class Weather(val lat: Double, val lon: Double, json: JSONObject) {
    var currentWeather: CurrentWeather = CurrentWeather(0, -500.0, -1.0, "")
    val weeklyWeather: ArrayList<DailyWeather> = ArrayList()
    val hourlyWeather: ArrayList<CurrentWeather> = ArrayList()
    var timezone: String = ""

    init {
        timezone = json.getString("timezone")
        setCurrentWeather(json.getJSONObject("current"), json.getJSONArray("hourly").getJSONObject(0))
        setHourlyWeather(json.getJSONArray("hourly"))
        setDailyWeather(json.getJSONArray("daily"))
    }

    /**
     * Initializes the current weather value with the correct values from the JSON object
     *
     * @param current the current weather portion of the JSON string
     * @param hour the current hour portion of the JSON string
     */
    private fun setCurrentWeather(current: JSONObject, hour: JSONObject) {
        val time = current.getInt("dt")
        val temp = current.getDouble("temp")
        val precip = hour.getDouble("pop")
        val condition = current.getJSONArray("weather").getJSONObject(0).getString("main")

        currentWeather = CurrentWeather(time, temp, precip, condition)
    }

    /**
     * Creates weather objects for each hour of the next 48 hours and adds them to the hourlyWeather list
     *
     * @param hourly the hourly weather JSON array
     */
    private fun setHourlyWeather(hourly: JSONArray) {
        var i = 0
        while (i < 48) {
            val hour = hourly.getJSONObject(i)
            val time = hour.getInt("dt")
            val temp = hour.getDouble("temp")
            val precip = hour.getDouble("pop")
            val condition = hour.getJSONArray("weather").getJSONObject(0).getString("main")

            hourlyWeather.add(CurrentWeather(time, temp, precip, condition))

            i++
        }
    }

    /**
     * Creates weather objects for each day of the next 7 days and adds them to the weeklyWeather list
     *
     * @param daily the daily weather JSON array
     */
    private fun setDailyWeather(daily: JSONArray) {
        var i = 0
        while (i < 7) {
            val day = daily.getJSONObject(i)
            val time = day.getInt("dt")
            val temp = day.getJSONObject("temp")
            val minTemp = temp.getDouble("min")
            val maxTemp = temp.getDouble("max")
            val precip = day.getDouble("pop")
            val condition = day.getJSONArray("weather").getJSONObject(0).getString("main")

            weeklyWeather.add(DailyWeather(time, minTemp, maxTemp, precip, condition))

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
