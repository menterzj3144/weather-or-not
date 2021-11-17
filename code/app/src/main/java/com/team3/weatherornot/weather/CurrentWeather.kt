package com.team3.weatherornot.weather

import java.time.ZonedDateTime

/**
 * Stores the data for the weather at a given point in time
 *
 * @property time the date and time + timezone for the day this weather is for
 * @property temp the current temperature at this time
 * @property precip the chance of precipitation for the day
 * @property condition the sky condition for the day
 * @constructor Create [CurrentWeather]
 */
class CurrentWeather(val time: ZonedDateTime, val temp: Int, val precip: Int,
                     val condition: String, val weatherImgId: String) {
}
