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

/**
 * A simple [Fragment] subclass.
 * Use the [SelectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get info from the database then make the dropdown menu
        val weatherActivities = Dao.getJson(view.context.applicationContext)
        createDropDownMenu(view, weatherActivities)
    }

    private fun createDropDownMenu(view: View, weatherActivities: List<WeatherActivity>) {
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

                for (activity in weatherActivities) {
                    if (activity.Activity_Name == it.toString()) {
                        findActForDay(activity,view)
                    }
                }
            }

    }

    private fun findActForDay(activity: WeatherActivity, view: View) {
        val dayListTV = view.findViewById<TextView>(R.id.select_list)
        dayListTV.text = ""

        APIManager.getInstance()?.getWeatherForLocation(44.8113, -91.4985) { weather ->

            for (day in weather.weeklyWeather){
                if (day.minTemp >= activity.Min_Temperature && day.maxTemp <= activity.Max_Temperature) {
                    if (activity.Weather_Type.contains(day.condition)) {
                        dayListTV.append(day.getFullDayName() +" ➝ "+day.minTemp +"°F - " +day.maxTemp +"°F"+ "\n")
                    }
                }
            }
            if (dayListTV.text.isEmpty()) {
                dayListTV.text = getString(R.string.no_suggested_days)
            }
        }

    }

}


