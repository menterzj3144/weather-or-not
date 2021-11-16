package com.team3.weatherornot.weather

/**
 * Stores the data for the weather on a given day
 *
 * @property time the time for the day this weather is for
 * @property minTemp the min temperature for the day
 * @property maxTemp the max temperature for the day
 * @property precip the chance of precipitation for the day
 * @property condition the sky condition for the day
 * @constructor Create [DailyWeather]
 */
class DailyWeather(val time: Int, val minTemp: Double, val maxTemp: Double, val precip: Double, val condition: String) {

}