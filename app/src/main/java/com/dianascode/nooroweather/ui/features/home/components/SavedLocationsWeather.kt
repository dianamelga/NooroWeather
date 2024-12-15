package com.dianascode.nooroweather.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dianascode.nooroweather.R
import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.ui.common.utils.AsyncImagePlaceHolder
import com.dianascode.nooroweather.ui.common.utils.TestIdentifiers
import com.dianascode.nooroweather.ui.common.utils.toWeatherFormat
import com.dianascode.nooroweather.ui.theme.Gray
import com.dianascode.nooroweather.ui.theme.GrayLabel
import com.dianascode.nooroweather.ui.theme.GrayTertiary
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme

@Composable
fun SavedLocationsWeather(
    savedLocations: List<Weather>,
    modifier: Modifier = Modifier
) {
    val weather = savedLocations.lastOrNull()
    Column(
        modifier = modifier.fillMaxWidth().testTag(TestIdentifiers.savedLocations),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = modifier.fillMaxWidth().weight(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https:${weather?.weatherCondition}",
                contentDescription = "Icon from URL",
                modifier = modifier.size(123.dp, 123.dp),
                contentScale = ContentScale.Crop,
                placeholder = AsyncImagePlaceHolder
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = weather?.cityName ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "Example Icon",
                    modifier = Modifier.padding(start = 8.dp).size(21.dp),
                    tint = Color.Black,
                )
            }
            Text(
                text = weather?.temperature?.toWeatherFormat() ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )

            Row(modifier = Modifier
                .width(274.dp)
                .height(75.dp)
                .background(color = Gray, shape = RoundedCornerShape(16.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Metric(
                    label = stringResource(R.string.humidity),
                    value = "${weather?.humidity.toString()} %"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Metric(
                    label = stringResource(R.string.uv),
                    value = weather?.uvIndex?.toInt().toString()
                )
                Spacer(modifier = Modifier.width(8.dp))
                Metric(
                    label = stringResource(R.string.feels_like),
                    value = weather?.feelsLikeTemperature?.toWeatherFormat() ?: ""
                )
            }
        }
    }
}

@Composable
private fun Metric(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            color = GrayLabel,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = GrayTertiary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun SavedLocationsPreview() {
    NooroWeatherTheme {
        SavedLocationsWeather(
            savedLocations = listOf(
                Weather(
                    cityName = "Mumbai",
                    temperature = 45f,
                    weatherCondition = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                    humidity = 45,
                    uvIndex = 1f,
                    feelsLikeTemperature = 45f
                )
            )
        )
    }
}