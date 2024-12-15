package com.dianascode.nooroweather.di

import com.dianascode.nooroweather.data.repository.IWeatherRepository
import com.dianascode.nooroweather.domain.usecases.IWeatherUseCases
import com.dianascode.nooroweather.domain.usecases.WeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherUseCasesModule {

    @Singleton
    @Provides
    fun provideWeatherUseCases(weatherRepository: IWeatherRepository): IWeatherUseCases {
        return WeatherUseCases(weatherRepository = weatherRepository)
    }
}