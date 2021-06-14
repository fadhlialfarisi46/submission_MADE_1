package com.example.sub1made.core.data.source.local

import com.example.sub1made.core.data.source.local.entity.MovieEntity
import com.example.sub1made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao){

    companion object{
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}