package com.ferdi.weatherapp.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int?,
    @SerialName("speed")
    val speed: Double?,
    @SerialName("gust")
    val gust: Double?
)