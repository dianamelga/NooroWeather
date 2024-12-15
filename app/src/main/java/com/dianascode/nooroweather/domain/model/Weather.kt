package com.dianascode.nooroweather.domain.model

data class Weather(
    val cityName: String,
    val temperature: Float,
    val weatherCondition: String,
    val humidity: Int,
    val uvIndex: Float,
    val feelsLikeTemperature: Float
) {
    companion object {
        fun dummy(): Weather {
            return Weather(
                cityName = "Mumbai",
                temperature = 45f,
                weatherCondition = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                humidity = 45,
                uvIndex = 1f,
                feelsLikeTemperature = 45f
            )
        }
    }
}