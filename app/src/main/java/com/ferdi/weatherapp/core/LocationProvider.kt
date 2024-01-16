package com.ferdi.weatherapp.core

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.internal.resumeCancellableWith
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProvider(private val context: Context) {
    private lateinit var fusedLocationProvider: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    suspend fun getCurrentUserLocation(): Pair<Double, Double> {
        val cancellationSource = CancellationTokenSource()
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)
        return suspendCoroutine { continuation ->
            fusedLocationProvider.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                cancellationSource.token
            ).addOnSuccessListener {
                if (it != null) {
                    continuation.resume(Pair(it.latitude, it.longitude))
                }
            }.addOnFailureListener {

            }
        }
    }
}