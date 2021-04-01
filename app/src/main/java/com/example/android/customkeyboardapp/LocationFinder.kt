package com.example.android.customkeyboardapp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import java.util.*


class LocationFinder {
    private var timer: Timer? = null
    private var locationManager: LocationManager? = null
    private var locationResult: LocationResult? = null
    private var gpsEnabled = false
    private var networkEnabled = false
    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, result: LocationResult?): Boolean {
        locationResult = result
        if (locationManager == null) {
            locationManager = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        try {
            gpsEnabled = locationManager!!
                .isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }
        try {
            networkEnabled = locationManager!!
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }

        // stop if no providers are enabled
        if (!gpsEnabled && !networkEnabled) {
            return false
        }
        if (gpsEnabled) {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0f, locationListenerGps
            )
        }
        if (networkEnabled) {
            locationManager!!.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0f, locationListenerNetwork
            )
        }
        timer = Timer()
        timer!!.schedule(GetLastLocation(), 20000)
        return true
    }

    var locationListenerGps: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timer!!.cancel()
            locationResult!!.gotLocation(location)
            locationManager!!.removeUpdates(this)
            locationManager!!.removeUpdates(locationListenerNetwork)
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }
    var locationListenerNetwork: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timer!!.cancel()
            locationResult!!.gotLocation(location)
            locationManager!!.removeUpdates(this)
            locationManager!!.removeUpdates(locationListenerGps)
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    internal inner class GetLastLocation : TimerTask() {
        @SuppressLint("MissingPermission")
        override fun run() {
            locationManager!!.removeUpdates(locationListenerGps)
            locationManager!!.removeUpdates(locationListenerNetwork)
            var netLocation: Location? = null
            var gpsLocation: Location? = null
            if (gpsEnabled) gpsLocation = locationManager!!
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (networkEnabled) netLocation = locationManager!!
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            // if there are both values use the latest one
            if (gpsLocation != null && netLocation != null) {
                if (gpsLocation.time > netLocation.time) locationResult!!.gotLocation(gpsLocation) else locationResult!!.gotLocation(
                    netLocation
                )
                return
            }
            if (gpsLocation != null) {
                locationResult!!.gotLocation(gpsLocation)
                return
            }
            if (netLocation != null) {
                locationResult!!.gotLocation(netLocation)
                return
            }
            locationResult!!.gotLocation(null)
        }
    }

    // Represents a Location Result
    abstract class LocationResult {
        abstract fun gotLocation(location: Location?)
    }
}