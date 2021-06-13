package com.example.sub1made.core.data.source.remote.network

import com.example.sub1made.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<ListMovieResponse>
}