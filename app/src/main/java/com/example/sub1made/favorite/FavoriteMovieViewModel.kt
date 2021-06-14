package com.example.sub1made.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sub1made.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val fovMovie = movieUseCase.getFavoriteMovie().asLiveData()
}