package com.ferdi.weatherapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdi.weatherapp.core.LocationProvider
import com.ferdi.weatherapp.core.getCurrentTime
import com.ferdi.weatherapp.data.LatLngState
import com.ferdi.weatherapp.data.mapper.toPreferredWeatherModel
import com.ferdi.weatherapp.domain.use_case.get_weather_use_case.GetWeatherUseCase
import com.ferdi.weatherapp.domain.weather.WeatherDetail
import com.ferdi.weatherapp.domain.weatherinfostate.WeatherInfoState
import com.ferdi.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: LocationProvider,
    private val weatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _currentLatLng = MutableStateFlow(LatLngState())
    val currentLatLng = _currentLatLng.asStateFlow()
    private val _weatherDetail = MutableStateFlow(WeatherDetail())
    val weatherDetail = _weatherDetail.asStateFlow()
    private val _isServiceAvailable = MutableStateFlow(false)
    val isServiceAvailable = _isServiceAvailable.asStateFlow()
    private val _weatherInfoState = MutableStateFlow(WeatherInfoState.LOADING)
    val weatherInfoState = _weatherInfoState.asStateFlow()
    private val _currentTick = MutableStateFlow("00:00")
    val currentTick = _currentTick.asStateFlow()
    fun getServiceState(data: Boolean) {
        _isServiceAvailable.update {
            data
        }
        if(!data){
            _weatherInfoState.update {
                WeatherInfoState.ERROR
            }
        }
    }

    init {
        tickTimer()
    }

    private fun tickTimer() = viewModelScope.launch(Dispatchers.IO) {
        while (true) {
            delay(1000L)
            _currentTick.update {
                getCurrentTime()
            }
        }
    }

    fun getCurrentWeatherData(latitude: Double, longitude: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            when (val res = weatherUseCase.getWeatherInfoImpl(latitude, longitude)) {
                is Resource.Success -> {
                    _weatherInfoState.update {
                        WeatherInfoState.SUCCESS
                    }
                    _weatherDetail.update {
                        res.result.toPreferredWeatherModel()
                    }
                }

                is Resource.Error -> {
                    _weatherInfoState.update {
                        WeatherInfoState.ERROR
                    }
                }

                is Resource.Loading -> {
                    _weatherInfoState.update {
                        WeatherInfoState.LOADING
                    }
                }
            }
        }

    fun getCurrentLocation() = viewModelScope.launch(Dispatchers.IO) {
        service.getCurrentUserLocation().let { data ->
            _currentLatLng.update {
                LatLngState(data.first, data.second)
            }
        }
    }
}