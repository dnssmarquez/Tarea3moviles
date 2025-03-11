package com.example.tarea3ejercicio2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea3ejercicio2.api.RetrofitClient
import com.example.tarea3ejercicio2.model.Movie
import com.example.tarea3ejercicio2.model.MovieResponse
import com.example.tarea3ejercicio2.ui.theme.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadMovies()
    }

    private fun loadMovies() {
        RetrofitClient.instance.getPopularMovies("T7b244cbca49eb4e1227259eb01052fd3").enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    adapter = MovieAdapter(movies)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "Error cargando películas: ${t.message}")
            }
        })
    }
}