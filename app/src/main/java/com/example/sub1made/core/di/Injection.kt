package com.example.sub1made.core.di

import android.content.Context
import com.example.sub1made.core.data.MovieRepository
import com.example.sub1made.core.data.source.local.LocalDataSource
import com.example.sub1made.core.data.source.local.room.MovieDatabase
import com.example.sub1made.core.data.source.remote.RemoteDataSource
import com.example.sub1made.core.data.source.remote.network.ApiConfig
import com.example.sub1made.core.domain.repository.IMovieRepository
import com.example.sub1made.core.domain.usecase.MovieInteractor
import com.example.sub1made.core.domain.usecase.MovieUseCase
import com.example.sub1made.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IMovieRepository{
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase{
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}