package com.team3.weatherornot.database
import android.content.Context
import java.io.IOException

/**
 * Get json data from asset file.
 *
 * @param context application context
 * @param fileName file name
 * @return json string
 */
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}