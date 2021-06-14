package com.example.sub1made.core.domain.repository

import com.example.sub1made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovies(): Flow<com.example.sub1made.core.data.Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}