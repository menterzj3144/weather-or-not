package com.team3.weatherornot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.team3.weatherornot.R
import com.team3.weatherornot.api.APIManager
import com.team3.weatherornot.database.Dao
import com.team3.weatherornot.database.WeatherActivity
import com.team3.weatherornot.weather.DailyWeather

/**
 * A simple [Fragment] subclass.
 * Use the [SuggestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuggestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get info from the database then make the dropdown menu
        val weatherActivities = Dao.getJson(view.context.applicationContext)
        createDropDownMenu(view, weatherActivities)
    }

    private fun createDropDownMenu(view: View, weatherActivities: List<WeatherActivity>) {
        // get the weather to fill the dropdown with the next 7 days
        APIManager.getInstance()?.getWeatherForLocation(44.8113, -91.4985) { weather ->

            val items: List<String> = weather.weeklyWeather.map { it.getFullDayName() } //might want to format the day differently
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
            val textField = view.findViewById<TextInputLayout>(R.id.suggest_drop_down)
            (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

            // this will execute after something is selected in the dropdown menu
            textField.editText?.doAfterTextChanged {
                val selectedDayTV = view.findViewById<TextView>(R.id.suggest_date)
                selectedDayTV.text = it.toString()

                for (day in weather.weeklyWeather) {
                    if (day.getFullDayName() == it.toString()) {
                        findActivitiesForDay(weatherActivities, day, view)
                    }
                }
            }
        }
    }

    //TODO() write a test for this
    private fun findActivitiesForDay(weatherActivities: List<WeatherActivity>, day: DailyWeather, view: View) {
        val activityListTV = view.findViewById<TextView>(R.id.suggest_list)
        activityListTV.text = ""

        for (activity in weatherActivities) {
            if (day.minTemp >= activity.Min_Temperature && day.maxTemp <= activity.Max_Temperature) {
                if (activity.Weather_Type.contains(day.condition)) {
                    activityListTV.append(activity.Activity_Name + "\n")
                }
            }
        }

        if (activityListTV.text.isEmpty()) {
            activityListTV.text = R.string.no_suggested_activity.toString()
        }
    }
}