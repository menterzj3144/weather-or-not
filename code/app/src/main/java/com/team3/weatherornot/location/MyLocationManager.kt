package com.team3.weatherornot.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Service
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.lang.Exception
import kotlin.math.roundToInt
import kotlin.system.exitProcess

/**
 * A custom location manager class
 *
 * @property activity the context of the activity
 * @property callback the callback function to be executed when the location is acquired
 * @constructor Create [MyLocationManager]
 */
class MyLocationManager(private val activity: Activity, val callback: (lat: Double, lon: Double) -> Unit) {
    private var gpsEnabled = false

    private var location: Location? = null

    private var locationManager: LocationManager? = null

    /**
     * Checks if the app has location permission. If it doesn't, request it
     */
    fun requestPermissions() {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //ask for permission
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSION_REQUEST_LOCATION_CODE
            )
        } else {
            //we have permission, get location
            getLocation()
        }
    }

    /**
     * Function to be executed from the overridden onRequestPermissionsResult function in an activity.
     * Checks if permission has been granted. If not, shows a dialog box explaining why we need permission
     *
     * @param requestCode the permission request code
     * @param grantResults the permissions that have been granted by the user
     */
    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_LOCATION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission granted, get location
                getLocation()
            } else {
                //permission not granted, explain why we need it
                AlertDialog.Builder(activity)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs location permissions to function properly, please accept to use location")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        requestPermissions()
                    }.setNegativeButton(
                        "Close App"
                    ) {_, _ ->
                        activity.finish()
                        exitProcess(0)
                    }
                    .create()
                    .show()
            }
        }
    }

    /**
     * Gets the user's current location and calls the provided callback function
     */
    @SuppressLint("MissingPermission") //getLocation is only called after permissions are granted
    private fun getLocation() {
        try {
            locationManager = activity.getSystemService(Service.LOCATION_SERVICE) as LocationManager

            gpsEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (gpsEnabled) {
                location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                if (location != null) {
                    callback(
                        (location!!.latitude * 10000).roundToInt().toDouble() / 10000,
                        (location!!.longitude * 10000).roundToInt().toDouble() / 10000
                    )
                }
            } else {
                Toast.makeText(activity, "GPS provider not enabled.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        //fine location request code
        private const val PERMISSION_REQUEST_LOCATION_CODE = 99
    }
}