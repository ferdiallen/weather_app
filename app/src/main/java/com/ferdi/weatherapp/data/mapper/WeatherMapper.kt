package com.ferdi.weatherapp.data.mapper

import com.ferdi.weatherapp.domain.model.WeatherModel
import com.ferdi.weatherapp.domain.weather.WeatherDetail

fun WeatherModel.toPreferredWeatherModel(): WeatherDetail {
    return WeatherDetail(
        cityName = this.name ?: "",
        temperature = this.main?.temp ?: 0.0,
        feelsLike = this.main?.feelsLike ?: 0.0,
        icon = "https://openweathermap.org/img/wn/${this.weather?.firstOrNull()?.icon}@2x.png",
        windSpeed = this.wind?.speed ?: 0.0,
        humidity = this.main?.humidity ?: 0,
        visibility = this.visibility ?: 0,
        airPressure = this.main?.pressure ?: 0,
        description = this.weather?.firstOrNull()?.description ?: ""
    )
}