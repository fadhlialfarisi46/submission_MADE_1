package com.example.sub1made.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sub1made.core.domain.usecase.MovieUseCase

class MenuViewModel (movieUseCase: MovieUseCase): ViewModel() {

    val movie = movieUseCase.getMovies().asLiveData()
}