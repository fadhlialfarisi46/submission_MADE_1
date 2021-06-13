package com.example.sub1made.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.sub1made.core.data.source.local.entity.MovieEntity
import com.example.sub1made.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){

    companion object{
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getMovies(): LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}