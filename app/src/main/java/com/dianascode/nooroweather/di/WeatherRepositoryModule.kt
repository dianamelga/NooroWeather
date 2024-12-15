package com.dianascode.nooroweather.di

import com.dianascode.nooroweather.data.local.WeatherDatabase
import com.dianascode.nooroweather.data.remote.WeatherApiService
import com.dianascode.nooroweather.data.repository.IWeatherRepository
import com.dianascode.nooroweather.data.repository.WeatherRepository
import com.dianascode.nooroweather.shared.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherRepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(apiService: WeatherApiService, database: WeatherDatabase, @IODispatcher dispatcher: CoroutineDispatcher): IWeatherRepository {
        return WeatherRepository(
            weatherApiService = apiService,
            weatherDao = database.weatherDao(),
            ioDispatcher = dispatcher
        )
    }
}