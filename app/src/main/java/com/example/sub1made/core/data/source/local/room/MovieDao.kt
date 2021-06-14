package com.example.sub1made.core.data.source.local.room

import androidx.room.*
import com.example.sub1made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}