package com.dianascode.nooroweather.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.domain.usecases.IWeatherUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeViewModel(
    private val useCases: IWeatherUseCases
): ViewModel() {
    val savedLocationsWeather = useCases.getSavedWeathersFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _locationFilter = MutableStateFlow("")
    val locationFilter = _locationFilter.asStateFlow()

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    val viewState = _viewState.asStateFlow()

    init {
        observeLocationFilterChanges()
    }

    fun searchLocation(location: String) {
        _locationFilter.value = location
        if (location.isEmpty()) {
            _viewState.value = HomeViewState.Idle
        }
    }

    fun saveLocationWeather(weather: Weather) {
        viewModelScope.launch {
            useCases.saveWeather(weather)
            searchLocation("")
        }
    }

    private fun observeLocationFilterChanges() {
        viewModelScope.launch {
            _locationFilter
                .filter { it.length >= 4 }
                .onEach { _viewState.value = HomeViewState.Loading }
                .distinctUntilChanged()
                .collectLatest { location ->
                    val result = useCases.getWeather(location)
                    if (result.isSuccess) {
                        _viewState.value = HomeViewState.Success(result.getOrNull())
                    } else {
                        _viewState.value = HomeViewState.Error(
                            result.exceptionOrNull() ?: Exception("Something went wrong")
                        )
                    }
                }
        }
    }
}

sealed class HomeViewState {
    object Idle : HomeViewState()
    object Loading : HomeViewState()
    data class Success(val locationWeatherFound: Weather?) : HomeViewState()
    data class Error(val exception: Throwable) : HomeViewState()
}