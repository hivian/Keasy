@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.utilities.location

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


object LocationHandler {

    /**
     * Gets the last known location, calling the specified [onSuccess] on success, [onFailure] otherwise.
     */
    @RequiresPermission(anyOf = [ Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION ])
    inline fun getLastLocation(context : Context, crossinline onSuccess : (Location) -> Unit, crossinline onFailure : () -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    onSuccess(location)
                } else {
                    onFailure()
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                onFailure()
            }
    }

    /**
     * Requests location updates with default [interval], [fastestInterval] and [priority] values,
     * calling the specified [onSuccess] on success.
     */
    @RequiresPermission(anyOf = [ Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION ])
    inline fun requestLocationUpdate(context : Context,
                                     interval : Long = 10000,
                                     fastestInterval : Long = 5000,
                                     priority : Int = LocationRequest.PRIORITY_HIGH_ACCURACY,
                                     crossinline onSuccess : (Location) -> Unit) {
        val locationRequest = LocationRequest.create()
        locationRequest.interval = interval
        locationRequest.fastestInterval = fastestInterval
        locationRequest.priority = priority
        val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.locations?.forEach { location ->
                    if (location != null) {
                        onSuccess(location)
                    }
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(context)
            .requestLocationUpdates(locationRequest, mLocationCallback, null)
    }
}