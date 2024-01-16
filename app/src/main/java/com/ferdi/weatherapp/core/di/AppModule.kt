package com.ferdi.weatherapp.core.di

import android.content.Context
import com.ferdi.weatherapp.core.LocationProvider
import com.ferdi.weatherapp.data.source.api.WeatherInfo
import com.ferdi.weatherapp.data.source.repository.WeatherInfoImpl
import com.ferdi.weatherapp.domain.use_case.get_weather_use_case.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocationData(@ApplicationContext context: Context) =
        LocationProvider(context)

    @Provides
    @Singleton
    fun providesKtorClient() = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    @Provides
    @Singleton
    fun providesWeatherInfo(client: HttpClient): WeatherInfo = WeatherInfoImpl(client)

    @Provides
    @Singleton
    fun providesWeatherDataUseCase(weatherInfo: WeatherInfo) = GetWeatherUseCase(weatherInfo)
}