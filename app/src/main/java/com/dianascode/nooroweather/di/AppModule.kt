package com.dianascode.nooroweather.di

import android.content.Context
import androidx.room.Room
import com.dianascode.nooroweather.BuildConfig
import com.dianascode.nooroweather.data.local.WeatherDatabase
import com.dianascode.nooroweather.data.remote.WeatherApiService
import com.dianascode.nooroweather.data.remote.interceptors.ApiKeyInterceptor
import com.dianascode.nooroweather.data.repository.IWeatherRepository
import com.dianascode.nooroweather.data.repository.WeatherRepository
import com.dianascode.nooroweather.domain.usecases.IWeatherUseCases
import com.dianascode.nooroweather.domain.usecases.WeatherUseCases
import com.dianascode.nooroweather.ui.features.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .build()
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get()) // Inject the OkHttpClient
            .build()
    }

    single {
        get<Retrofit>().create(WeatherApiService::class.java) // Create WeatherApiService
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(), // Inject the application context
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    single {
        get<WeatherDatabase>().weatherDao() // Provide the WeatherDao instance
    }
}

val repositoryModule = module {
    single<IWeatherRepository> {
        WeatherRepository(
            weatherApiService = get(),
            weatherDao = get<WeatherDatabase>().weatherDao(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }
}

val useCasesModule = module {
    single<IWeatherUseCases> {
        WeatherUseCases(
            weatherRepository = get()
        )
    }
}

val coroutineModule = module {
    single(named("IODispatcher")) { Dispatchers.IO }
    single(named("DefaultDispatcher")) { Dispatchers.Default }
    single(named("MainDispatcher")) { Dispatchers.Main }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) } // Inject IWeatherUseCases into HomeViewModel
}
