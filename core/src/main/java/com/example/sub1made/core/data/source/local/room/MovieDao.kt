package com.example.sub1made.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntities")
    fun getMovies(): Flow<List<com.example.sub1made.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM movieEntities where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<com.example.sub1made.core.data.source.local.entity.MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<com.example.sub1made.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: com.example.sub1made.core.data.source.local.entity.MovieEntity)
}