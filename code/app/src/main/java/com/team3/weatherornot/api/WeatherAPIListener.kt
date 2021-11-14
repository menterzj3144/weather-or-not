package com.team3.weatherornot.api

interface WeatherAPIListener<T> {
    fun getResult(result: T)
}