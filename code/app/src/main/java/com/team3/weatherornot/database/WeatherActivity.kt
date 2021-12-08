package com.team3.weatherornot.database

data class WeatherActivity(val activity_id: Int, val activity_name: String, val activity_Desc: String,
                           val weather_Type: Array<String>, val min_Temperature: Int, val max_Temperature: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherActivity

        if (activity_id != other.activity_id) return false
        if (activity_name != other.activity_name) return false
        if (activity_Desc != other.activity_Desc) return false
        if (!weather_Type.contentEquals(other.weather_Type)) return false
        if (min_Temperature != other.min_Temperature) return false
        if (max_Temperature != other.max_Temperature) return false

        return true
    }

    override fun hashCode(): Int {
        var result = activity_id
        result = 31 * result + activity_name.hashCode()
        result = 31 * result + activity_Desc.hashCode()
        result = 31 * result + weather_Type.contentHashCode()
        result = 31 * result + min_Temperature
        result = 31 * result + max_Temperature
        return result
    }
}