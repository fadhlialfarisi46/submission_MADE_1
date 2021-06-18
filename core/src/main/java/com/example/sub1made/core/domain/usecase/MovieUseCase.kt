package com.example.sub1made.core.domain.usecase

import com.example.sub1made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<com.example.sub1made.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}