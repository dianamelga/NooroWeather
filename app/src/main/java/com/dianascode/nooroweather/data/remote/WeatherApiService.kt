package com.dianascode.nooroweather.data.remote

import com.dianascode.nooroweather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "yes"
    ) : WeatherResponse
}