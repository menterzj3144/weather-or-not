package com.team3.weatherornot.DatabaseFiles

data class WeatherActivity(val Activity_id: Int, val Activity_name: String, val Activity_Desc: String,
val Weather_Type: Array<String>, val Min_Temperature: Int, val Max_Temperature: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherActivity

        if (Activity_id != other.Activity_id) return false
        if (Activity_name != other.Activity_name) return false
        if (Activity_Desc != other.Activity_Desc) return false
        if (!Weather_Type.contentEquals(other.Weather_Type)) return false
        if (Min_Temperature != other.Min_Temperature) return false
        if (Max_Temperature != other.Max_Temperature) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Activity_id
        result = 31 * result + Activity_name.hashCode()
        result = 31 * result + Activity_Desc.hashCode()
        result = 31 * result + Weather_Type.contentHashCode()
        result = 31 * result + Min_Temperature
        result = 31 * result + Max_Temperature
        return result
    }
}