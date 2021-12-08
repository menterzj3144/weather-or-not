package com.team3.weatherornot

import com.team3.weatherornot.weather.Weather
import org.junit.Test
import android.content.Context
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import com.team3.weatherornot.api.APIManager

/**
 * Tests for the APIManager class
 */
@RunWith(RobolectricTestRunner::class)
class APITests {
    private val lat = 44.811348
    private val lon = -91.498497

    private val mockContext: Context = mock()

    private val apiManager: APIManager? = APIManager.instantiate(mockContext)


    @Test
    fun test_timezone() {
        apiManager?.getWeatherForLocation(lat, lon) {
            assert(it.timezone == "America/Chicago")
        }
    }


    @Test
    fun test_getCurrentWeather() {
        apiManager?.getWeatherForLocation(lat, lon) {
            val current = it.currentWeather

            assert(current.time.toEpochSecond() > 0)

            val temp = current.temp
            assert(temp > -200)
            assert(temp < 200)

            val precip = current.precip
            assert(precip >= 0)
            assert(precip <= 1)

            assert(current.condition.isNotBlank())
        }
    }

    @Test
    fun test_getHourlyWeather() {
        apiManager?.getWeatherForLocation(lat, lon) {
            val hourly = it.hourlyWeather
            assert(hourly.size == 48)

            for (hour in hourly) {
                assert(hour.time.toEpochSecond() > 0)

                val temp = hour.temp
                assert(temp > -200)
                assert(temp < 200)

                val precip = hour.precip
                assert(precip >= 0)
                assert(precip <= 1)

                assert(hour.condition.isNotBlank())
            }
        }
    }

    @Test
    fun test_getHourlyWeatherForHours() {
        apiManager?.getWeatherForLocation(lat, lon) {
            val hourly = it.getHourlyWeatherForHours(10)
            assert(hourly.size == 10)

            for (hour in hourly) {
                assert(hour.time.toEpochSecond() > 0)

                val temp = hour.temp
                assert(temp > -200)
                assert(temp < 200)

                val precip = hour.precip
                assert(precip >= 0)
                assert(precip <= 1)

                assert(hour.condition.isNotBlank())
            }
        }
    }

    @Test
    fun test_getWeeklyWeather() {
        apiManager?.getWeatherForLocation(lat, lon) {
            val weekly = it.weeklyWeather
            assert(weekly.size == 7)

            for (day in weekly) {
                assert(day.time.toEpochSecond() > 0)

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
    }

    @Test
    fun test_getSpecificDayWeather() {
        apiManager?.getWeatherForLocation(lat, lon) {
            val day = it.getSpecificDayWeather(1)
            assert(day.time.toEpochSecond() > 0)

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
}