package com.dianascode.nooroweather.ui.features.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.ui.common.utils.AsyncImagePlaceHolder
import com.dianascode.nooroweather.ui.common.utils.shimmerBackground
import com.dianascode.nooroweather.ui.common.utils.toWeatherFormat
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme

@Composable
fun LocationWeatherCard(
    weather: Weather?,
    isLoading: Boolean,
    onClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        if (isLoading) {
            CardPlaceHolder()
        } else {
            weather?.let {
                CardContent(weather = it, onClick = onClick)
            }
        }
    }
}

@Composable
private fun CardContent(
    weather: Weather,
    onClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 20.dp)
            .fillMaxWidth()
            .clickable { onClick(weather) }
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = weather.temperature.toWeatherFormat(),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        AsyncImage(
            model = "https:${weather.weatherCondition}",
            contentDescription = "Icon from URL",
            modifier = modifier.size(83.dp, 67.dp),
            contentScale = ContentScale.Crop,
            placeholder = AsyncImagePlaceHolder
        )
    }
}

@Composable
private fun CardPlaceHolder(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .shimmerBackground(RoundedCornerShape(4.dp))
                    .size(40.dp, 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = modifier
                    .shimmerBackground(RoundedCornerShape(4.dp))
                    .size(80.dp, 50.dp)
            )
        }
        Box(
            modifier = modifier
                .shimmerBackground(RoundedCornerShape(4.dp))
                .size(83.dp)
        )
    }
}

@Preview
@Composable
private fun LocationWeatherCardPreview() {
    NooroWeatherTheme {
        LocationWeatherCard(
            isLoading = false,
            weather = Weather.dummy(),
            onClick = {}
        )
    }
}