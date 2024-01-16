package com.ferdi.weatherapp.domain.use_case.get_weather_use_case

import com.ferdi.weatherapp.data.source.api.WeatherInfo
import com.ferdi.weatherapp.domain.model.WeatherModel
import com.ferdi.weatherapp.utils.Resource
import java.io.IOException

class GetWeatherUseCase(
    private val weatherInfo: WeatherInfo
) {
    suspend fun getWeatherInfoImpl(lat: Double, long: Double): Resource<WeatherModel> {
        val res = try {
            Resource.Success(weatherInfo.getWeatherData(lat, long))
        } catch (e: Exception) {
            Resource.Error( e.localizedMessage ?: "Unknown Error")
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage ?: "Unknown Error")
        }
        return res
    }
}