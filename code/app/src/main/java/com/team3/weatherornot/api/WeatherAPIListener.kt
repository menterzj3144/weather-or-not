package com.team3.weatherornot.api

/**
 * Weather api listener.
 *
 * @param T
 * @constructor Create empty constructor for weather api listener
 */
interface WeatherAPIListener<T> {
    fun getResult(result: T)
}