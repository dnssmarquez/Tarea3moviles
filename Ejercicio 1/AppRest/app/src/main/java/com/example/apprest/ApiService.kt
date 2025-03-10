package com.example.apprest

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/mensaje")
    fun obtenerMensaje(): Call<Map<String, String>>
}
