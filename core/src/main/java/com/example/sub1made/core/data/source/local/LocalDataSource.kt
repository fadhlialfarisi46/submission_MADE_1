package com.example.sub1made.core.data.source.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: com.example.sub1made.core.data.source.local.room.MovieDao){

    fun getMovies(): Flow<List<com.example.sub1made.core.data.source.local.entity.MovieEntity>> = movieDao.getMovies()

    fun getFavoriteMovie(): Flow<List<com.example.sub1made.core.data.source.local.entity.MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<com.example.sub1made.core.data.source.local.entity.MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: com.example.sub1made.core.data.source.local.entity.MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}