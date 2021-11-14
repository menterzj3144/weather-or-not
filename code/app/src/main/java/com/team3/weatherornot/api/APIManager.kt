package com.team3.weatherornot.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.team3.weatherornot.weather.Weather

/**
 * Code found at https://stackoverflow.com/questions/28172496/android-volley-how-to-isolate-requests-in-another-class
 *
 * Singleton class for making API calls using application context
 */
class APIManager private constructor(context: Context) {
    private val apiKey = "345319f45656517a0f88de5d5cdf0a7d"
    var requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)


    /**
     * Gets the weather information for the passed in location and calls the listener function to
     * get the result back to the caller
     */
    fun getWeatherForLocation(lat: Double, lon: Double, listener: WeatherAPIListener<Weather>) {
        val apiURL: String = "https://api.openweathermap.org/data/2.5/onecall?appid=$apiKey" +
                "&lat=$lat&lon=$lon&units=imperial"

        // make api call.
        val request = JsonObjectRequest(Request.Method.GET, apiURL, null,
            {
                listener.getResult(Weather(it))
            },
            {
                println("Error! $it")
            }
        )

        requestQueue.add(request)
    }

    companion object {
        private var instance: APIManager? = null
        @Synchronized
        fun getInstance(context: Context): APIManager? {
            if (null == instance) instance = APIManager(context)
            return instance
        }

        //this is so you don't need to pass context each time
        @JvmStatic
        @Synchronized
        fun getInstance(): APIManager? {
            checkNotNull(instance) {
                APIManager::class.java.simpleName +
                        " is not initialized, call getInstance(...) first"
            }
            return instance
        }
    }
}