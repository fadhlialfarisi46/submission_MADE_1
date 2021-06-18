package com.example.sub1made.core.utils

import com.example.sub1made.core.data.source.remote.response.MovieResponse
import com.example.sub1made.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<com.example.sub1made.core.data.source.local.entity.MovieEntity>{
        val movieList = ArrayList<com.example.sub1made.core.data.source.local.entity.MovieEntity>()
        input.map {
            val movie = com.example.sub1made.core.data.source.local.entity.MovieEntity(
                id = it.id,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<com.example.sub1made.core.data.source.local.entity.MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath.toString(),
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        com.example.sub1made.core.data.source.local.entity.MovieEntity(
            id = input.id,
            overview = input.overview,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            isFavorite = input.isFavorite
        )
}