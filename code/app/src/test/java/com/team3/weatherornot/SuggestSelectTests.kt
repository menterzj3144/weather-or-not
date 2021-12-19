package com.team3.weatherornot

import com.team3.weatherornot.database.WeatherActivity
import com.team3.weatherornot.weather.DailyWeather
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.ZoneId
import java.time.ZonedDateTime

@RunWith(RobolectricTestRunner::class)
class SuggestSelectTests {
    private val utils = SuggestSelectUtils()
    private val time = ZonedDateTime.of(2021, 12, 29, 0, 0, 0, 0, ZoneId.of("America/Chicago"))

    @Test
    fun testFindDayForActivity() {
        val weatherActivity = WeatherActivity(1, "Running", "Running outside", arrayOf("Clear", "Rain", "Clouds"), 32, 90)
        val time = ZonedDateTime.of(2021, 12, 29, 0, 0, 0, 0, ZoneId.of("America/Chicago"))

        val weeklyWeather: ArrayList<DailyWeather> = ArrayList()

        weeklyWeather.add(DailyWeather(time, 10, 24, 0, 0, 0, 0, 0, "Clouds", "0"))
        weeklyWeather.add(DailyWeather(time, 34, 40, 0, 0, 0, 0, 0, "Snow", "0"))
        weeklyWeather.add(DailyWeather(time, 40, 55, 0, 0, 0, 0, 0, "Clear", "0"))
        weeklyWeather.add(DailyWeather(time, 40, 55, 0, 0, 0, 0, 0, "Rain", "0"))
        weeklyWeather.add(DailyWeather(time, 10, 24, 0, 0, 0, 0, 0, "Clouds", "0"))
        weeklyWeather.add(DailyWeather(time, 50, 70, 0, 0, 0, 0, 0, "Clouds", "0"))
        weeklyWeather.add(DailyWeather(time, 85, 110, 0, 0, 0, 0, 0, "Clouds", "0"))


        val result = utils.findDayForActivity(weeklyWeather, weatherActivity).split("\n")
        assert(result.size == 4)
    }

    @Test
    fun testFindActivityForDay() {
        val weatherActivities: ArrayList<WeatherActivity> = ArrayList()

        weatherActivities.add(WeatherActivity(1, "Running", "", arrayOf("Clear", "Rain", "Clouds"), 32, 90))
        weatherActivities.add(WeatherActivity(2, "Snowboarding", "", arrayOf("Clear", "Snow", "Clouds"), -10, 32))
        weatherActivities.add(WeatherActivity(3, "Picnic", "", arrayOf("Clear", "Clouds"), 50, 90))
        weatherActivities.add(WeatherActivity(4, "Indoor Basketball", "", arrayOf("Clear", "Rain", "Clouds", "Snow"), -20, 120))
        weatherActivities.add(WeatherActivity(5, "Hiking", "", arrayOf("Clear", "Clouds"), 40, 92))

        val day = DailyWeather(time, 10, 24, 0, 0, 0, 0, 0, "Clouds", "0")

        val result = utils.findActivitiesForDay(weatherActivities, day).split("\n")
        assert(result.size == 3)
    }
}