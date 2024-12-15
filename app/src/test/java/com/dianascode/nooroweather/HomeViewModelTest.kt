package com.dianascode.nooroweather

import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.domain.usecases.IWeatherUseCases
import com.dianascode.nooroweather.ui.features.home.HomeViewModel
import com.dianascode.nooroweather.ui.features.home.HomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val mockUseCases: IWeatherUseCases = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(mockUseCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchLocation updates location filter and sets Idle state for empty input`() = runTest {

        viewModel.searchLocation("")

        assertEquals("", viewModel.locationFilter.value)
        assertTrue(viewModel.viewState.value is HomeViewState.Idle)
    }

    @Test
    fun `saveLocationWeather calls use case and clears location filter`() = runTest {
        val weather = Weather.dummy().copy(cityName = "Paris")

        viewModel.saveLocationWeather(weather)

        advanceUntilIdle()

        verify(mockUseCases).saveWeather(weather)
        assertEquals("", viewModel.locationFilter.value)
    }

    @Test
    fun `observeLocationFilterChanges sets Success state when search succeeds`() = runTest {
        val location = "London"
        val weather = Weather.dummy().copy(cityName = location)

        whenever(mockUseCases.getWeather(location)).thenReturn(Result.success(weather))

        viewModel.searchLocation(location)
        advanceUntilIdle()

        val viewState = viewModel.viewState.value
        assertTrue(viewState is HomeViewState.Success)
        assertEquals(weather, (viewState as HomeViewState.Success).locationWeatherFound)
    }

    @Test
    fun `observeLocationFilterChanges sets Error state when search fails`() = runTest {
        val location = "Unknown"
        val error = Exception("Location not found")

        whenever(mockUseCases.getWeather(location)).thenReturn(Result.failure(error))

        viewModel.searchLocation(location)
        advanceUntilIdle()

        val viewState = viewModel.viewState.value
        assertTrue(viewState is HomeViewState.Error)
        assertEquals(error, (viewState as HomeViewState.Error).exception)
    }

    @Test
    fun `observeLocationFilterChanges ignores input shorter than 4 characters`() = runTest {
        viewModel.searchLocation("Lon")
        advanceUntilIdle()

        verify(mockUseCases, never()).getWeather(any())
        assertTrue(viewModel.viewState.value is HomeViewState.Idle)
    }
}
