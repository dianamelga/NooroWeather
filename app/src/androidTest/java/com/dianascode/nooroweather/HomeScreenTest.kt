package com.dianascode.nooroweather

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dianascode.nooroweather.domain.model.Weather
import com.dianascode.nooroweather.ui.common.utils.TestIdentifiers
import com.dianascode.nooroweather.ui.features.home.HomeScreen
import com.dianascode.nooroweather.ui.features.home.HomeViewModel
import com.dianascode.nooroweather.ui.features.home.HomeViewState
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockScreenState = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    private val mockLocationFilter = MutableStateFlow("")
    private val mockSavedLocationsWeather = MutableStateFlow<List<Weather>>(emptyList())

    private val mockViewModel: HomeViewModel = mock(HomeViewModel::class.java).apply {
        whenever(viewState).thenReturn(mockScreenState)
        whenever(locationFilter).thenReturn(mockLocationFilter)
        whenever(savedLocationsWeather).thenReturn(mockSavedLocationsWeather)
    }

    private fun waitUIToBeRendered() {
        composeTestRule.setContent {
            NooroWeatherTheme {
                HomeScreen(
                    viewModel = mockViewModel,
                )
            }
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun testLoadingStateWhenSearching() {
        // Arrange
        val location = "Paris"

        // Mock the flow and view states
        mockScreenState.value = HomeViewState.Loading
        // Act: Simulate typing in the search bar
        mockLocationFilter.value = location

        // Set up the composable for testing
        waitUIToBeRendered()

        composeTestRule.onNodeWithTag(TestIdentifiers.searchBar).assertIsDisplayed()

        // Assert: Check that the loading state is displayed
        composeTestRule.onNodeWithTag(TestIdentifiers.loadingState).assertIsDisplayed()
    }

    @Test
    fun testSuccessStateWhenWeatherFound() {
        // Arrange
        val location = "Paris"
        val mockResponse = Weather.dummy().copy(cityName = location)

        // Mock the flow and view states
        mockScreenState.value = HomeViewState.Success(mockResponse)
        // Act: Simulate typing in the search bar
        mockLocationFilter.value = location

        // Set up the composable for testing
        waitUIToBeRendered()

        composeTestRule.onNodeWithTag(TestIdentifiers.searchBar).assertIsDisplayed()

        // Assert: Check that the weather card is displayed (success state)
        composeTestRule.onNodeWithTag(TestIdentifiers.weatherCard).assertIsDisplayed()
    }

    @Test
    fun testSavedLocationsDisplay() {
        // Arrange
        val savedWeather = listOf(Weather.dummy())

        // Mock the flow to return saved weather
        mockSavedLocationsWeather.value = savedWeather

        // Set up the composable for testing
        waitUIToBeRendered()

        composeTestRule.onNodeWithTag(TestIdentifiers.searchBar).assertIsDisplayed()

        // Act: Simulate UI displaying saved locations
        composeTestRule.onNodeWithTag(TestIdentifiers.savedLocations).assertIsDisplayed()
    }
}
