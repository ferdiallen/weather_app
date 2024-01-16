package com.ferdi.weatherapp.utils

sealed class Resource<out T> {
    data class Success<out T>(val result: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}