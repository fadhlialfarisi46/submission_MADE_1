package com.example.sub1made.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sub1made.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where isFavorite = 1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}