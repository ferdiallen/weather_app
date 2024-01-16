package com.ferdi.weatherapp.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("feels_like")
    val feelsLike: Double?,
    @SerialName("humidity")
    val humidity: Int?,
    @SerialName("pressure")
    val pressure: Int?,
    @SerialName("temp")
    val temp: Double?,
    @SerialName("temp_max")
    val tempMax: Double?,
    @SerialName("temp_min")
    val tempMin: Double?,
    @SerialName("sea_level")
    val seaLevel:Int? = null,
    @SerialName("grnd_level")
    val groundLevel:Int? = null
)