package com.dianascode.nooroweather.domain.mappers

import com.dianascode.nooroweather.data.local.WeatherEntity
import com.dianascode.nooroweather.data.model.WeatherResponse
import com.dianascode.nooroweather.domain.model.Weather

fun WeatherEntity.toWeather() = Weather(
    cityName = this.cityName,
    temperature = this.temperature,
    weatherCondition = this.weatherCondition,
    humidity = this.humidity,
    uvIndex = this.uvIndex,
    feelsLikeTemperature = this.feelsLikeTemperature
)

fun WeatherResponse.toWeather() = Weather(
    cityName = this.location.name,
    temperature = this.current.tempF,
    weatherCondition = this.current.condition.icon,
    humidity = this.current.humidity,
    uvIndex = this.current.uv,
    feelsLikeTemperature = this.current.feelslikeF
)

fun Weather.toEntity() = WeatherEntity(
    cityName = this.cityName,
    temperature = this.temperature,
    weatherCondition = this.weatherCondition,
    humidity = this.humidity,
    uvIndex = this.uvIndex,
    feelsLikeTemperature = this.feelsLikeTemperature
)