package com.ferdi.weatherapp.domain.weather


data class WeatherDetail(
    val cityName: String = "",
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val icon: String = "",
    val windSpeed: Double = 0.0,
    val humidity: Int = 0,
    val visibility: Int? = 0,
    val airPressure: Int? = 0,
    val description:String = ""
)
