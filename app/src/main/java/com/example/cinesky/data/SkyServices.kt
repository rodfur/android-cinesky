package com.example.cinesky.data

import com.example.cinesky.data.response.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyServices {

    @GET("Movies")
    fun getMovies(): Call<List<MovieDetailsResponse>>
}