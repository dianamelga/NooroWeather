package com.dianascode.nooroweather.domain.usecases

import com.dianascode.nooroweather.data.repository.IWeatherRepository
import com.dianascode.nooroweather.domain.mappers.toEntity
import com.dianascode.nooroweather.domain.mappers.toWeather
import com.dianascode.nooroweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherUseCases @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : IWeatherUseCases {

    override suspend fun getWeather(cityName: String): Result<Weather> {
        val result = weatherRepository.searchWeather(cityName)
        return when {
            result.isSuccess -> {
                result.getOrNull()?.toWeather()?.let { weatherDomain ->
                    Result.success(weatherDomain)
                } ?: Result.failure(Exception("Empty data"))
            }
            result.isFailure -> {
                Result.failure(result.exceptionOrNull() ?: genericError())
            }
            else -> {
                Result.failure(genericError())
            }
        }
    }

    override fun getSavedWeathersFlow(): Flow<List<Weather>> {
        return weatherRepository.getSavedWeathersFlow().map { entities ->
            entities.map { entity -> entity.toWeather() }
        }
    }

    override suspend fun saveWeather(weather: Weather): Result<Unit> {
        return weatherRepository.deleteAllAndInsertWeather(weather.toEntity())
    }

    private fun genericError() = Exception("Something went wrong")
}