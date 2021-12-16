package com.team3.weatherornot.weather

import android.content.Context
import android.location.Geocoder
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
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
class Weather(val lat: Double, val lon: Double, json: JSONObject) {
    //the time the weather object was created/updated
    val updateTime: String
    lateinit var currentWeather: CurrentWeather
    val weeklyWeather: ArrayList<DailyWeather> = ArrayList()
    val hourlyWeather: ArrayList<CurrentWeather> = ArrayList()
    var timezone: String = ""
    var cityState: String = ""

    init {
        timezone = json.getString("timezone")
        updateTime = " " + SimpleDateFormat("h:mm a", Locale.getDefault())
            .format(Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(timezone))).time)

        setCurrentWeather(json.getJSONObject("current"), json.getJSONArray("hourly").getJSONObject(0))
        setHourlyWeather(json.getJSONArray("hourly"))
        setDailyWeather(json.getJSONArray("daily"))
    }

    /**
     * Convert epoch seconds from the API to a ZonedDateTime
     *
     * @param time the epoch value of the time from the API
     * @return [ZonedDateTime] the converted date and time
     */
    private fun convertTimeToDateTime(time: Long): ZonedDateTime {
        val instant = Instant.ofEpochSecond(time)
        val zone = ZoneId.of(timezone)
        return ZonedDateTime.ofInstant(instant, zone)
    }

    /**
     * Initializes the current weather value with the correct values from the JSON object
     *
     * @param current the current weather portion of the JSON string
     * @param hour the current hour portion of the JSON string
     */
    private fun setCurrentWeather(current: JSONObject, hour: JSONObject) {
        val dt = convertTimeToDateTime(current.getLong("dt"))
        val condition = current.getJSONArray("weather").getJSONObject(0)

        currentWeather = CurrentWeather(dt, current.getInt("temp"), (hour.getDouble("pop") * 100).toInt(),
            condition.getString("main"), condition.getString("icon"))
    }

    /**
     * Converts the latitude and longitude coordinates into a city and state string
     *
     * @param context application context
     * @return a string of the city and state names
     */
    fun getCityState(context: Context, callback: (city: String) -> Unit) {
        Thread {
            if (cityState.isNotBlank()) {
                callback(cityState)
            } else {
                try {
                    val geo = Geocoder(context, Locale.getDefault())
                    val addresses = geo.getFromLocation(lat, lon, 1)
                    cityState = addresses[0].locality + ", " + addresses[0].adminArea
                    callback(cityState)
                } catch (e: Exception) {
                    callback("$lat, $lon")
                }
            }
        }.start()
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
            val dt = convertTimeToDateTime(hour.getLong("dt"))
            val condition = hour.getJSONArray("weather").getJSONObject(0)

            hourlyWeather.add(CurrentWeather(dt, hour.getInt("temp"), (hour.getDouble("pop") * 100).toInt(),
                condition.getString("main"), condition.getString("icon")))

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
            val dt = convertTimeToDateTime(day.getLong("dt"))
            val temp = day.getJSONObject("temp")
            val condition = day.getJSONArray("weather").getJSONObject(0)

            weeklyWeather.add(DailyWeather(dt, temp.getInt("min"), temp.getInt("max"),
                temp.getInt("morn"), temp.getInt("day"), temp.getInt("eve"),
                temp.getInt("night"), (day.getDouble("pop") * 100).toInt(),
                condition.getString("main"), condition.getString("icon")))

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
    fun getHourlyWeatherForHours(numHours: Int): ArrayList<CurrentWeather> {
        val list = ArrayList<CurrentWeather>()
        var i = 0
        while (i < numHours) {
            list.add(hourlyWeather[i])
            i++
        }
        return list
    }
}
