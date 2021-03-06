package com.example.sub1made.core.domain.usecase

import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase{
    override fun getMovies()= movieRepository.getMovies()

    override fun getFavoriteMovie()= movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)


}