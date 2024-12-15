package com.dianascode.nooroweather

import com.dianascode.nooroweather.data.local.WeatherDao
import com.dianascode.nooroweather.mock.mockWeatherResponse
import com.dianascode.nooroweather.data.remote.WeatherApiService
import com.dianascode.nooroweather.data.repository.WeatherRepository
import com.dianascode.nooroweather.domain.mappers.toEntity
import com.dianascode.nooroweather.domain.model.Weather
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository
    private val mockWeatherApiService: WeatherApiService = mock()
    private val mockWeatherDao: WeatherDao = mock()
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = WeatherRepository(
            weatherApiService = mockWeatherApiService,
            weatherDao = mockWeatherDao,
            ioDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchWeather returns success when API call is successful`() = runTest {
        val cityName = "Mumbai"
        val mockResponse = mockWeatherResponse
        whenever(mockWeatherApiService.getWeather(cityName)).thenReturn(mockResponse)

        val result = repository.searchWeather(cityName)

        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `searchWeather returns failure when API call throws an exception`() = runTest {
        val cityName = "Mumbai"
        whenever(mockWeatherApiService.getWeather(cityName)).thenThrow(RuntimeException(java.net.UnknownHostException()))

        val result = repository.searchWeather(cityName)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
    }

    @Test
    fun `getSavedWeathersFlow fetches data from DAO`() = runTest {
        val mockData = listOf(
            Weather.dummy().toEntity(),
            Weather.dummy().toEntity().copy(cityName = "London")
        )
        whenever(mockWeatherDao.getWeathersFlow()).thenReturn(flowOf(mockData))

        val result = repository.getSavedWeathersFlow().toList()

        assertEquals(1, result.size)
        assertEquals(mockData, result[0])
    }

    @Test
    fun `deleteAllAndInsertWeather deletes all and inserts weather`() = runTest {
        val weather = Weather.dummy().toEntity()

        val result = repository.deleteAllAndInsertWeather(weather)

        assertTrue(result.isSuccess)
        verify(mockWeatherDao).deleteAllAndInsertWeather(weather)
    }

    @Test
    fun `deleteAllAndInsertWeather returns failure on exception`() = runTest {
        val weather = Weather.dummy().toEntity()
        whenever(mockWeatherDao.deleteAllAndInsertWeather(weather)).thenThrow(RuntimeException())

        val result = repository.deleteAllAndInsertWeather(weather)

        assertTrue(result.isFailure)
    }
}
