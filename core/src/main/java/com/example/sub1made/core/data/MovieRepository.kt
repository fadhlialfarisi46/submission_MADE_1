package com.example.sub1made.core.data

import com.example.sub1made.core.data.source.remote.network.ApiResponse
import com.example.sub1made.core.data.source.remote.response.MovieResponse
import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.core.domain.repository.IMovieRepository
import com.example.sub1made.core.utils.AppExecutors
import com.example.sub1made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: com.example.sub1made.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.example.sub1made.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository{

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : com.example.sub1made.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }


}