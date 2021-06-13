package com.example.sub1made.detail

import androidx.lifecycle.ViewModel
import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) = movieUseCase.setFavoriteMovie(movie, newState)
}