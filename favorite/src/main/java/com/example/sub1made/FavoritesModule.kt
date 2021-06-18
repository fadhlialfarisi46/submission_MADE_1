package com.example.sub1made

import com.example.sub1made.favorite.FavoriteMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
}