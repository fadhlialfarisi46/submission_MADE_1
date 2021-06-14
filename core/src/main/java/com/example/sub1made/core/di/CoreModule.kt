package com.example.sub1made.core.di

import androidx.room.Room
import com.example.sub1made.core.data.source.remote.network.ApiService
import com.example.sub1made.core.domain.repository.IMovieRepository
import com.example.sub1made.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module{
    factory { get<com.example.sub1made.core.data.source.local.room.MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            com.example.sub1made.core.data.source.local.room.MovieDatabase::class.java,
            "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.example.sub1made.core.data.source.local.LocalDataSource(get()) }
    single { com.example.sub1made.core.data.source.remote.RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { com.example.sub1made.core.data.MovieRepository(get(), get(), get()) }
}