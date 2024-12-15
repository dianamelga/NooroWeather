package com.dianascode.nooroweather.data.repository

import android.accounts.NetworkErrorException
import com.dianascode.nooroweather.data.local.WeatherDao
import com.dianascode.nooroweather.data.local.WeatherEntity
import com.dianascode.nooroweather.data.model.WeatherResponse
import com.dianascode.nooroweather.data.remote.WeatherApiService
import com.dianascode.nooroweather.shared.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
    ): IWeatherRepository {

    override suspend fun searchWeather(cityName: String): Result<WeatherResponse> {
        return try {
            withContext(ioDispatcher) {
                Result.success(weatherApiService.getWeather(cityName = cityName))
            }
        } catch(e: Exception) {
            Result.failure(handleError(e))
        }
    }

    override fun getSavedWeathersFlow(): Flow<List<WeatherEntity>> {
        return weatherDao.getWeathersFlow() // Room already handles this on Dispatcher.IO by default
    }

    override suspend fun deleteAllAndInsertWeather(weather: WeatherEntity): Result<Unit> {
        return try {
            withContext(ioDispatcher) {
                weatherDao.deleteAllAndInsertWeather(weather)
                Result.success(Unit)
            }
        } catch(e: Exception) {
            Result.failure(handleError(e))
        }
    }

    private fun handleError(exception: Exception): Exception {
        return when (exception) {
            is java.net.UnknownHostException -> NetworkErrorException("Network error")
            else -> exception
        }
    }
}