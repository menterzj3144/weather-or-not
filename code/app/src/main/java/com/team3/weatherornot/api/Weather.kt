package com.team3.weatherornot.api

import android.app.Activity
import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Weather(context: Activity): Application() {
    private val metadata: JSONObject = JSONObject()
    private val queue: RequestQueue = Volley.newRequestQueue(context)
    val currentWeather: CurrentWeather = CurrentWeather(0, -500, -1, "")
    val weeklyWeather: ArrayList<DailyWeather> = ArrayList()
    val hourlyWeather: ArrayList<CurrentWeather> = ArrayList()
    private val apiKey = "345319f45656517a0f88de5d5cdf0a7d"

    init {
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

    fun executeRequest(lat: Double, lon: Double, lambda: (JSONObject) -> Unit) {
        val apiURL: String = "https://api.openweathermap.org/data/2.5/onecall?appid=$apiKey" +
                "&lat=$lat&lon=$lon"

        // make api call.
        val request = JsonObjectRequest(Request.Method.GET, apiURL, null,
            {
                lambda(it)
            },
            {
                println("Error! $it")
            }
        )

        queue.add(request)
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
