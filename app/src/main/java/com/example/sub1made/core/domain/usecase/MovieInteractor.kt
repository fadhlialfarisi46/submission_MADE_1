package com.example.sub1made.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.sub1made.core.data.Resource
import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase{
    override fun getMovies(): LiveData<Resource<List<Movie>>> = movieRepository.getMovies()

    override fun getFavoriteMovie(): LiveData<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)


}