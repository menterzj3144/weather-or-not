package com.team3.weatherornot

import android.content.Context
import com.team3.weatherornot.database.Dao
import com.team3.weatherornot.database.WeatherActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SelectTests {
    val mockContext: Context = mock()

    val weatherActivities = Dao.getJson(mockContext)

    @Test
    fun clearAnd60() {
        var sampleTemp = 60
        var sampleConditions = "Clear"

        var activityList = mutableListOf<String>()

        for (activity in weatherActivities) {
            if (activity.Max_Temperature >= sampleTemp && activity.Max_Temperature <= sampleTemp) {
                if (activity.Weather_Type.contains(sampleConditions)) {
                    activityList.add(activity.Activity_Name)
                }
            }
        }

        assert(activityList.count() == 11) // we expect 11 activities to be returned.
    }


}