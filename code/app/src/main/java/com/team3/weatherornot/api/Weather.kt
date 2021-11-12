package com.team3.weatherornot.api

import android.app.Activity
import android.app.Application
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Weather(lat: Double, lon: Double, context: Activity): Application() {
    private val metadata: JSONObject = JSONObject()
    val currentWeather: CurrentWeather
    val weeklyWeather: ArrayList<DailyWeather>
    val hourlyWeather: ArrayList<CurrentWeather>

    init {
        val apiKey = "345319f45656517a0f88de5d5cdf0a7d"

        val queue = Volley.newRequestQueue(context)
        val apiURL: String = "https://api.openweathermap.org/data/2.5/onecall?appid=$apiKey" +
                "&lat=$lat&lon=$lon"

        // make api call.
        val request = JsonObjectRequest(
            Request.Method.GET,
            apiURL,
            null,
            {
                parseJSONObject(it)
            },
            {
                println("Error! $it")
            }
        )

        queue.add(request)

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

    private fun parseJSONObject(obj: JSONObject) {
        metadata.put("lat", obj.get("lat"))
        metadata.put("lon", obj.get("lon"))
        metadata.put("tz", obj.get("timezone"))
        metadata.put("tz_offset", obj.get("timezone_offset"))
        metadata.put("last_update", obj.getJSONObject("current").get("dt"))
    }


    fun getTimeZone(): String {
        return metadata.get("tz").toString()
    }

    fun getSpecificDayWeather(day: Int): DailyWeather {
        return weeklyWeather[day]
    }

    fun getHourlyWeatherForHours(hours: Int): ArrayList<CurrentWeather> {
        return hourlyWeather
    }
}
