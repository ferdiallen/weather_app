package com.ferdi.weatherapp.data.source.api

import com.ferdi.weatherapp.domain.model.WeatherModel

interface WeatherInfo {
    suspend fun getWeatherData(lat: Double, long: Double): WeatherModel
}