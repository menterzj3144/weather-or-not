package com.team3.weatherornot.api

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest


fun main() {
    APIWrapper(44.811348, -91.498497)
}

class APIWrapper(val lat: Double, val lon: Double) {
    val currentWeather: CurrentWeather
    val weeklyWeather: ArrayList<DailyWeather>
    val hourlyWeather: ArrayList<CurrentWeather>


    init {
        val apiKey = "345319f45656517a0f88de5d5cdf0a7d"

        val apiURL: String = "https://api.openweathermap.org/data/2.5/onecall?appid=$apiKey" +
                "&lat=$lat&lon=$lon"

        // make api call.

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, apiURL, null,
            { response -> print(response.toString())},
            { error -> print("error! reason: $error") })

        //pass in JSON information to CurrentWeather objects so that it's easier to return them and
        // access them outside of the wrapper
        currentWeather = CurrentWeather(0, -500, -1, "")
        weeklyWeather = ArrayList()
        hourlyWeather = ArrayList()

        //for each day in the week
        var i = 0
        while (i < 7) {
            //use DailyWeather objects since there is different information needed
            weeklyWeather.add(DailyWeather(0, -500, 500, -1, ""))
            i++
        }

        //for 48 hours
        i = 0
        while (i < 48) {
            hourlyWeather.add(CurrentWeather(0, -500, -1, ""))
            i++
        }
    }


    fun getTimeZone(): String {
        return ""
    }

    fun getSpecificDayWeather(day: Int): DailyWeather {
        return weeklyWeather[day]
    }

    fun getHourlyWeatherForHours(hours: Int): ArrayList<CurrentWeather> {
        return hourlyWeather
    }
}
