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
import com.team3.weatherornot.SuggestSelectUtils
import com.team3.weatherornot.database.WeatherActivity
import com.team3.weatherornot.weather.DailyWeather
import com.team3.weatherornot.weather.Weather

/**
 * The fragment for suggesting activities based on a selected day
 *
 * @property weather the current weather information
 * @property weatherActivities the list of activities from the database file
 * @constructor Create [SuggestFragment] creates the fragment
 */
class SuggestFragment(private val weather: Weather, private val weatherActivities: List<WeatherActivity>) : Fragment() {

    /**
     * Creates the view for the fragment
     *
     * @param inflater Inflater
     * @param container Container
     * @param savedInstanceState Saved instance state
     * @return [View] or null
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggest, container, false)
    }

    /**
     * Creates the dropdown menu when the view has been created
     *
     * @param view View
     * @param savedInstanceState Saved instance state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get info from the database then make the dropdown menu
        createDropDownMenu(view)
    }

    /**
     * Creates the dropdown menu and fills it with the days of the week
     *
     * @param view the view to display to
     */
    private fun createDropDownMenu(view: View) {
        val items: List<String> = weather.weeklyWeather.map { it.getDayNameAndDate() } //might want to format the day differently
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        val textField = view.findViewById<TextInputLayout>(R.id.suggest_drop_down)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        // this will execute after something is selected in the dropdown menu
        textField.editText?.doAfterTextChanged {
            val selectedDayTV = view.findViewById<TextView>(R.id.suggest_date)
            selectedDayTV.text = it.toString()

            val activityListTV = view.findViewById<TextView>(R.id.suggest_list)

            for (day in weather.weeklyWeather) {
                if (day.getDayNameAndDate() == it.toString()) {
                    activityListTV.text = SuggestSelectUtils().findActivitiesForDay(weatherActivities, day)
                }
            }
        }
    }

}