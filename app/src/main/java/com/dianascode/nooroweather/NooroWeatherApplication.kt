package com.dianascode.nooroweather

import android.app.Application
import com.dianascode.nooroweather.di.coroutineModule
import com.dianascode.nooroweather.di.databaseModule
import com.dianascode.nooroweather.di.networkModule
import com.dianascode.nooroweather.di.repositoryModule
import com.dianascode.nooroweather.di.useCasesModule
import com.dianascode.nooroweather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NooroWeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NooroWeatherApplication)
            modules(
                listOf(
                networkModule,
                databaseModule,
                repositoryModule,
                coroutineModule,
                    useCasesModule,
                    viewModelModule
                )
            )
        }
    }
}