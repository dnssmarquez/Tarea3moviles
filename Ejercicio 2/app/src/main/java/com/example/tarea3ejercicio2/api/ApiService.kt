package com.example.tarea3ejercicio2.api

import com.example.tarea3ejercicio2.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}