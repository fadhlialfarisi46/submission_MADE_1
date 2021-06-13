package com.example.sub1made.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.sub1made.core.data.Resource
import com.example.sub1made.core.domain.model.Movie

interface MovieUseCase {
    fun getMovies(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}