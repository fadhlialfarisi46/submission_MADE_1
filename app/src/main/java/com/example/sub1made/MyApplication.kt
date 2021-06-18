package com.example.sub1made

import android.app.Application
import com.example.sub1made.core.di.databaseModule
import com.example.sub1made.core.di.networkModule
import com.example.sub1made.core.di.repositoryModule
import com.example.sub1made.di.useCaseModule
import com.example.sub1made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}