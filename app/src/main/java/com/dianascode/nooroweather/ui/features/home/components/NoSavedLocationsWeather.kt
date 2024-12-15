package com.dianascode.nooroweather.ui.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dianascode.nooroweather.R
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme

@Composable
fun NoSavedLocationsWeather(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().weight(0.7f)
        ) {
            Text(
                text = stringResource(R.string.no_city_selected),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(R.string.please_search_for_city),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Preview
@Composable
fun NoSavedLocationsPreview() {
    NooroWeatherTheme {
        NoSavedLocationsWeather()
    }
}
