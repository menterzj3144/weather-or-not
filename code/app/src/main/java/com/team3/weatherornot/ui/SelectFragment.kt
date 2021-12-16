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
import com.team3.weatherornot.database.WeatherActivity
import com.team3.weatherornot.weather.Weather

/**
 * The fragment for suggesting days based on a selected activity
 *
 * @property weather the current weather information
 * @property weatherActivities the list of activities from the database file
 * @constructor Create [SuggestFragment] creates the fragment
 */
class SelectFragment(private val weather: Weather, private val weatherActivities: List<WeatherActivity>) : Fragment() {

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
        return inflater.inflate(R.layout.fragment_select, container, false)
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
     * Creates the dropdown menu and fills it with the activities from the database
     *
     * @param view the view to display to
     */
    private fun createDropDownMenu(view: View) {
        // get the list of activity names and display in drop down menu
        val namesList = mutableListOf<String>()
        for (activity in weatherActivities){
            namesList.add(activity.Activity_Name)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, namesList)
        val textField = view.findViewById<TextInputLayout>(R.id.select_drop_down)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        // this will execute after something is selected in the dropdown menu
        textField.editText?.doAfterTextChanged {
            val selectedActTV = view.findViewById<TextView>(R.id.selected_activity)
            selectedActTV.text = it.toString()

            val dayListTV = view.findViewById<TextView>(R.id.select_list)

            for (activity in weatherActivities) {
                if (activity.Activity_Name == it.toString()) {
                    dayListTV.text = findDayForActivity(activity)
                }
            }
        }
    }

    /**
     * Finds possible day for a given activity
     *
     * @param activity the activity to be compared against
     * @return the suggested days to be displayed
     */
    //TODO() Test for this?
    private fun findDayForActivity(activity: WeatherActivity): String {
        var daysString = ""

        for (day in weather.weeklyWeather){
            if (day.minTemp >= activity.Min_Temperature && day.maxTemp <= activity.Max_Temperature) {
                if (activity.Weather_Type.contains(day.condition)) {
                    daysString += day.getFullDayName() +" ➝ "+day.minTemp +"°F - " +day.maxTemp +"°F"+ "\n"
                }
            }
        }
        if (daysString.isEmpty()) {
            daysString = getString(R.string.no_suggested_days)
        }

        return daysString
    }

}


