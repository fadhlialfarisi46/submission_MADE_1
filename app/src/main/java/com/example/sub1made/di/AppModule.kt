package com.example.sub1made.di

import com.example.sub1made.core.domain.usecase.MovieInteractor
import com.example.sub1made.core.domain.usecase.MovieUseCase
import com.example.sub1made.detail.DetailMovieViewModel
import com.example.sub1made.home.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MenuViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}