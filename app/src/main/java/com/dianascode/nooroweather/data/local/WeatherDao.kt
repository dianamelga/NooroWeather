package com.dianascode.nooroweather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather")
    fun getWeathersFlow(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM weather WHERE cityName = :cityName")
    suspend fun getWeatherByCity(cityName: String): WeatherEntity?

    @Query("DELETE FROM weather WHERE cityName = :cityName")
    suspend fun deleteWeather(cityName: String)

    @Query("DELETE FROM weather")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAllAndInsertWeather(weather: WeatherEntity) {
        deleteAll()
        insertWeather(weather)
    }
}