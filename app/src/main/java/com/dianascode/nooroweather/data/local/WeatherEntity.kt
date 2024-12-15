package com.dianascode.nooroweather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val cityName: String,
    val temperature: Float,
    val weatherCondition: String,
    val humidity: Int,
    val uvIndex: Float,
    val feelsLikeTemperature: Float
)