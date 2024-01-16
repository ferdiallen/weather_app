package com.ferdi.weatherapp.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

val locationPermission = listOf(
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION
)

fun launchLocationPermissionCheck(
    context: Context,
    permissions: List<String> = emptyList(),
    launcher: ManagedActivityResultLauncher<List<String>, Boolean>
) {
    permissions.map {
        ContextCompat.checkSelfPermission(context, it)
    }.all {
        if (it == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            launcher.launch(permissions)
            false
        }
    }
}