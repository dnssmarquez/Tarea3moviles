package com.example.tarea3ejercicio2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search.json")
    Call<BookResponse> searchBooks(@Query("q") String query);
}