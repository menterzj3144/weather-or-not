package com.example.weatherornot


class APIWrapper(val lat: Double, val lon: Double) {
    val currentWeather: CurrentWeather
    val weeklyWeather: ArrayList<DailyWeather>
    val hourlyWeather: ArrayList<CurrentWeather>


    init {
        //make api call

        //pass in JSON information to CurrentWeather objects so that it's easier to return them and
        // access them outside of the wrapper
        currentWeather = CurrentWeather(0, -500, -1, "")
        weeklyWeather = ArrayList()
        hourlyWeather = ArrayList()

        var i = 0
        while (i < 7) {
            //use DailyWeather objects since there is different information needed
            weeklyWeather.add(DailyWeather(0, -500, 500, -1, ""))
            i++
        }

        i = 0
        while (i < 24) {
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
