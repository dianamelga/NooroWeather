package com.dianascode.nooroweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current
)

data class Location(
    @SerializedName("name") val name: String, // "London"
    @SerializedName("region") val region: String, // "City of London, Greater London"
    @SerializedName("country") val country: String, // "United Kingdom"
    @SerializedName("lat") val lat: Double, // 51.5171
    @SerializedName("lon") val lon: Double, // -0.1062
    @SerializedName("tz_id") val tzId: String, // "Europe/London"
    @SerializedName("localtime_epoch") val localtimeEpoch: Long, // 1734181174
    @SerializedName("localtime") val localtime: String // "2024-12-14 12:59"
)

data class Current(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long, // 1734180300
    @SerializedName("last_updated") val lastUpdated: String, // "2024-12-14 12:45"
    @SerializedName("temp_c") val tempC: Float, // 8.4
    @SerializedName("temp_f") val tempF: Float, // 47.1
    @SerializedName("is_day") val isDay: Int, // 1
    @SerializedName("condition") val condition: Condition,
    @SerializedName("wind_mph") val windMph: Float, // 8.9
    @SerializedName("wind_kph") val windKph: Float, // 14.4
    @SerializedName("wind_degree") val windDegree: Int, // 298
    @SerializedName("wind_dir") val windDir: String, // "WNW"
    @SerializedName("pressure_mb") val pressureMb: Float, // 1022.0
    @SerializedName("pressure_in") val pressureIn: Float, // 30.18
    @SerializedName("precip_mm") val precipMm: Float, // 0.0
    @SerializedName("precip_in") val precipIn: Float, // 0.0
    @SerializedName("humidity") val humidity: Int, // 76
    @SerializedName("cloud") val cloud: Int, // 25
    @SerializedName("feelslike_c") val feelslikeC: Float, // 6.0
    @SerializedName("feelslike_f") val feelslikeF: Float, // 42.8
    @SerializedName("windchill_c") val windchillC: Float, // 4.8
    @SerializedName("windchill_f") val windchillF: Float, // 40.6
    @SerializedName("heatindex_c") val heatindexC: Float, // 7.4
    @SerializedName("heatindex_f") val heatindexF: Float, // 45.3
    @SerializedName("dewpoint_c") val dewpointC: Float, // 2.9
    @SerializedName("dewpoint_f") val dewpointF: Float, // 37.2
    @SerializedName("vis_km") val visKm: Float, // 10.0
    @SerializedName("vis_miles") val visMiles: Float, // 6.0
    @SerializedName("uv") val uv: Float, // 0.5
    @SerializedName("gust_mph") val gustMph: Float, // 11.9
    @SerializedName("gust_kph") val gustKph: Float, // 19.1
    @SerializedName("air_quality") val airQuality: AirQuality
)

data class Condition(
    @SerializedName("text") val text: String, // "Partly cloudy"
    @SerializedName("icon") val icon: String, // "//cdn.weatherapi.com/weather/64x64/day/116.png"
    @SerializedName("code") val code: Int // 1003
)

data class AirQuality(
    @SerializedName("co") val co: Float, // 314.5
    @SerializedName("no2") val no2: Float, // 34.78
    @SerializedName("o3") val o3: Float, // 50.0
    @SerializedName("so2") val so2: Float, // 4.07
    @SerializedName("pm2_5") val pm25: Float, // 10.915
    @SerializedName("pm10") val pm10: Float, // 19.055
    @SerializedName("us-epa-index") val usEpaIndex: Int, // 1
    @SerializedName("gb-defra-index") val gbDefraIndex: Int // 1
)
