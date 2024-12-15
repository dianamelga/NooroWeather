package com.dianascode.nooroweather.domain.usecases
import com.dianascode.nooroweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface IWeatherUseCases {
    suspend fun getWeather(cityName: String): Result<Weather>
    fun getSavedWeathersFlow(): Flow<List<Weather>>
    suspend fun saveWeather(weather: Weather): Result<Unit>
}