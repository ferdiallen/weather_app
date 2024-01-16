package com.ferdi.weatherapp.utils

fun baseUrl(lat: Double, long: Double): String {
    return "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$long&appid=246870ca0491e4f355fa3c139dd60029&lang=ID&units=metric"
}