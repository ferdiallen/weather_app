package com.ferdi.weatherapp.data.source.repository

import com.ferdi.weatherapp.data.source.api.WeatherInfo
import com.ferdi.weatherapp.domain.model.WeatherModel
import com.ferdi.weatherapp.utils.baseUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class WeatherInfoImpl @Inject constructor(
    private val client: HttpClient
) : WeatherInfo{
    override suspend fun getWeatherData(lat: Double, long: Double): WeatherModel {
        return client.get(baseUrl(lat, long)){
            this.contentType(ContentType.Application.Json)
        }.body()
    }
}