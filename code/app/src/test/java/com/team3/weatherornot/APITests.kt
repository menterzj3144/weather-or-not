package com.team3.weatherornot

import com.team3.weatherornot.weather.Weather
import org.junit.Test
import androidx.test.core.app.ApplicationProvider
import android.content.Context
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
//import org.robolectric.shadows.httpclient
// import androidx.test.core.app.ApplicationProvider
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.api.WeatherAPIListener

/**
 * Tests for the API wrapper class
 */
@RunWith(RobolectricTestRunner::class)
class APITests {
    private val mockContext: Context = mock()

    private val apiManager: APIManager? = APIManager.instantiate(mockContext)

    //private val api =

        //Weather(44.811348, -91.498497) //EC

    @Test
    fun test_timezone() {


        val timezone = apiManager?.getWeatherForLocationAPI(44.811348, -91.498497, object: WeatherAPIListener<Weather> {
            override fun getResult(result: Weather) {
                assert(result.timezone == "America/Chicago")
            }
        })

    }

    /*
    @Test
    fun test_getCurrentWeather() {
        val current = api.currentWeather
        assert(current.time > 0)

        val temp = current.temp
        assert(temp > -200)
        assert(temp < 200)

        val precip = current.precip
        assert(precip >= 0)
        assert(precip <= 1)

        assert(current.condition.isNotBlank())
    }

    @Test
    fun test_getHourlyWeather() {
        val hourly = api.hourlyWeather
        assert(hourly.size == 48)

        for (hour in hourly) {
            assert(hour.time > 0)

            val temp = hour.temp
            assert(temp > -200)
            assert(temp < 200)

            val precip = hour.precip
            assert(precip >= 0)
            assert(precip <= 1)

            assert(hour.condition.isNotBlank())
        }
    }

    @Test
    fun test_getHourlyWeatherForHours() {
        val hourly = api.getHourlyWeatherForHours(10)
        assert(hourly.size != null)
        assert(hourly.size == 10)

        for (hour in hourly) {
            assert(hour.time > 0)
            assert(hour != null)

            val temp = hour.temp
            assert(temp > -200)
            assert(temp < 200)

            val precip = hour.precip
            assert(precip >= 0)
            assert(precip <= 1)

            assert(hour.condition.isNotBlank())
        }
    }

    @Test
    fun test_getWeeklyWeather() {
        val daily = api.weeklyWeather
        assert(daily.size == 7)

        for (day in daily) {
            assert(day.time > 0)

            val minTemp = day.minTemp
            assert(minTemp > -200)
            assert(minTemp < 200)

            val maxTemp = day.maxTemp
            assert(maxTemp > -200)
            assert(maxTemp < 200)

            val precip = day.precip
            assert(precip >= 0)
            assert(precip <= 1)

            assert(day.condition.isNotBlank())
        }
    }

    @Test
    fun test_getSpecificDayWeather() {
        val day = api.getSpecificDayWeather(2)

        assert(day.time > 0)

        val minTemp = day.minTemp
        assert(minTemp > -200)
        assert(minTemp < 200)

        val maxTemp = day.maxTemp
        assert(maxTemp > -200)
        assert(maxTemp < 200)

        val precip = day.precip
        assert(precip >= 0)
        assert(precip <= 1)

        assert(day.condition.isNotBlank())
    }
     */
}