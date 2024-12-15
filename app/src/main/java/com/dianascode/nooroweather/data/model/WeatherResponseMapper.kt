package com.dianascode.nooroweather.data.model

import com.dianascode.nooroweather.data.local.WeatherEntity

fun WeatherResponse.toEntity() = WeatherEntity(
    cityName = this.location.name,
    temperature = this.current.tempF,
    weatherCondition = this.current.condition.icon,
    humidity = this.current.humidity,
    uvIndex = this.current.uv,
    feelsLikeTemperature = this.current.feelslikeF
)