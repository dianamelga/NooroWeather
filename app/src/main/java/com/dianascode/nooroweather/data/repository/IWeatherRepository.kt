package com.dianascode.nooroweather.data.repository

import com.dianascode.nooroweather.data.local.WeatherEntity
import com.dianascode.nooroweather.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun searchWeather(cityName: String): Result<WeatherResponse>
    fun getSavedWeathersFlow(): Flow<List<WeatherEntity>>
    suspend fun deleteAllAndInsertWeather(weather: WeatherEntity): Result<Unit>

}