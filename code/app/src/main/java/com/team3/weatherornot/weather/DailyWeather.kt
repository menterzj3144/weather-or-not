package com.team3.weatherornot.weather

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Stores the data for the weather on a given day
 *
 * @property time the date and time + timezone for the day this weather is for
 * @property minTemp the min temperature for the day
 * @property maxTemp the max temperature for the day
 * @property precip the chance of precipitation for the day
 * @property condition the sky condition for the day
 * @constructor Create [DailyWeather]
 */
class DailyWeather(val time: ZonedDateTime, val minTemp: Int, val maxTemp: Int, val morning: Int,
                   val day: Int, val evening: Int, val night: Int, val precip: Int,
                   val condition: String, val weatherImgId: String) {

    //can write a test for this
    fun getDayAbbreviation(): String {
        return DateTimeFormatter.ofPattern("EEE").format(time)
    }

    fun getFullDayName(): String {
        return DateTimeFormatter.ofPattern("EEEE").format(time)
    }
}