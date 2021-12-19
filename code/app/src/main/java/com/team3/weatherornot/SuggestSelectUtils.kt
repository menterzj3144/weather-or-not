package com.team3.weatherornot

import com.team3.weatherornot.database.WeatherActivity
import com.team3.weatherornot.weather.DailyWeather

class SuggestSelectUtils {
    /**
     * Finds possible day for a given activity
     *
     * @param activity the activity to be compared against
     * @return the suggested days to be displayed
     */
    fun findDayForActivity(weeklyWeather: ArrayList<DailyWeather>, activity: WeatherActivity): String {
        var daysString = ""

        for (day in weeklyWeather){
            if (day.minTemp >= activity.Min_Temperature && day.maxTemp <= activity.Max_Temperature) {
                if (activity.Weather_Type.contains(day.condition)) {
                    daysString += day.getFullDayName() +" ➝ "+day.minTemp +"°F - " +day.maxTemp +"°F"+ "\n"
                }
            }
        }
        if (daysString.isEmpty()) {
            daysString = "No suggested days for this activity."
        }

        return daysString
    }

    /**
     * Finds possible activities for a given day
     *
     * @param day the day to be compared against
     * @return the suggested activities to be displayed
     */
    fun findActivitiesForDay(weatherActivities: List<WeatherActivity>, day: DailyWeather): String {
        var activitiesString = ""

        for (activity in weatherActivities) {
            //if the day temp range is within the activity temp range
            if (day.minTemp >= activity.Min_Temperature && day.maxTemp <= activity.Max_Temperature) {
                if (activity.Weather_Type.contains(day.condition)) {
                    activitiesString += activity.Activity_Name + "\n"
                }
            }
        }

        if (activitiesString.isEmpty()) {
            activitiesString = "No suggested activities for this day."
        }

        return activitiesString
    }
}