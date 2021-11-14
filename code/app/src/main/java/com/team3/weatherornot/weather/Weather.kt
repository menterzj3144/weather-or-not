package com.team3.weatherornot.weather

import org.json.JSONArray
import org.json.JSONObject

/**
 * Weather contains all of the necessary weather information from an API call
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


    private fun setCurrentWeather(current: JSONObject, hour: JSONObject) {
        val time = current.getInt("dt")
        val temp = current.getDouble("temp")
        val precip = hour.getDouble("pop")
        val condition = current.getJSONArray("weather").getJSONObject(0).getString("main")

        currentWeather = CurrentWeather(time, temp, precip, condition)
    }

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

    fun getSpecificDayWeather(day: Int): DailyWeather {
        return weeklyWeather[day]
    }

    fun getHourlyWeatherForHours(hours: Int): ArrayList<CurrentWeather> {
        return hourlyWeather
    }

}
