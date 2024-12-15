package com.dianascode.nooroweather.mock

import com.dianascode.nooroweather.data.model.AirQuality
import com.dianascode.nooroweather.data.model.Condition
import com.dianascode.nooroweather.data.model.Current
import com.dianascode.nooroweather.data.model.Location
import com.dianascode.nooroweather.data.model.WeatherResponse

val mockWeatherResponse = WeatherResponse(
    location = Location(
        name = "London",
        region = "City of London, Greater London",
        country = "United Kingdom",
        lat = 51.5171,
        lon = -0.1062,
        tzId = "Europe/London",
        localtimeEpoch = 1734181174L,
        localtime = "2024-12-14 12:59"
    ),
    current = Current(
        lastUpdatedEpoch = 1734180300L,
        lastUpdated = "2024-12-14 12:45",
        tempC = 8.4f,
        tempF = 47.1f,
        isDay = 1,
        condition = Condition(
            text = "Partly cloudy",
            icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
            code = 1003
        ),
        windMph = 8.9f,
        windKph = 14.4f,
        windDegree = 298,
        windDir = "WNW",
        pressureMb = 1022.0f,
        pressureIn = 30.18f,
        precipMm = 0.0f,
        precipIn = 0.0f,
        humidity = 76,
        cloud = 25,
        feelslikeC = 6.0f,
        feelslikeF = 42.8f,
        windchillC = 4.8f,
        windchillF = 40.6f,
        heatindexC = 7.4f,
        heatindexF = 45.3f,
        dewpointC = 2.9f,
        dewpointF = 37.2f,
        visKm = 10.0f,
        visMiles = 6.0f,
        uv = 0.5f,
        gustMph = 11.9f,
        gustKph = 19.1f,
        airQuality = AirQuality(
            co = 314.5f,
            no2 = 34.78f,
            o3 = 50.0f,
            so2 = 4.07f,
            pm25 = 10.915f,
            pm10 = 19.055f,
            usEpaIndex = 1,
            gbDefraIndex = 1
        )
    )
)
