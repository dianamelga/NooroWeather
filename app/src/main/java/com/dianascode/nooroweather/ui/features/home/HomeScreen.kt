package com.dianascode.nooroweather.ui.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.ui.features.home.components.NoSavedLocationsWeather
import com.dianascode.nooroweather.ui.features.home.components.SavedLocationsWeather
import com.dianascode.nooroweather.ui.features.search.LocationSearchBar
import com.dianascode.nooroweather.ui.features.search.LocationWeatherCard
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController? = null, // for this scope is not needed, but this can be used to navigate to another screen
    viewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val savedLocationsWeather by viewModel.savedLocationsWeather.collectAsState()
    val viewState by viewModel.viewState.collectAsState()
    val locationWeatherFound = (viewState as? HomeViewState.Success)?.locationWeatherFound
    val locationFilter by viewModel.locationFilter.collectAsState()

    HomeScreenContent(
        locationFilter = locationFilter,
        savedLocationsWeather = savedLocationsWeather,
        locationWeatherFound = locationWeatherFound,
        searchLocationWeather = viewModel::searchLocation,
        isLoading = viewState is HomeViewState.Loading,
        onLocationWeatherClick = viewModel::saveLocationWeather,
        modifier = modifier
    )
}

@Composable
private fun HomeScreenContent(
    locationFilter: String,
    savedLocationsWeather: List<Weather>,
    locationWeatherFound: Weather?,
    searchLocationWeather: (String) -> Unit,
    isLoading: Boolean,
    onLocationWeatherClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery = remember(locationFilter) { mutableStateOf(locationFilter) }

    Column(modifier = modifier.fillMaxSize()) {
        LocationSearchBar(
            query = searchQuery.value,
            onQueryChange = {
                searchQuery.value = it
                searchLocationWeather(searchQuery.value)
            }
        )

        if (searchQuery.value.isNotEmpty()) {
            LocationWeatherCard(
                isLoading = isLoading,
                weather = locationWeatherFound,
                onClick = onLocationWeatherClick
            )
        } else {
            if (savedLocationsWeather.isNotEmpty()) {
                SavedLocationsWeather(
                    savedLocations = savedLocationsWeather
                )
            } else {
                NoSavedLocationsWeather()
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    NooroWeatherTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeScreenContent(
                locationFilter = "",
                savedLocationsWeather = listOf(Weather.dummy()),
                locationWeatherFound =
                Weather.dummy(),
                searchLocationWeather = {},
                isLoading = false,
                modifier = Modifier.padding(innerPadding),
                onLocationWeatherClick = {}
            )
        }
    }
}