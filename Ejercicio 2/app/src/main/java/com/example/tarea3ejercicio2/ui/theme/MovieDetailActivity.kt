package com.example.tarea3ejercicio2.ui.theme

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tarea3ejercicio2.R

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var posterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Inicializamos las vistas
        titleTextView = findViewById(R.id.movie_title)
        overviewTextView = findViewById(R.id.movie_overview)
        posterImageView = findViewById(R.id.movie_poster)

        // Obtener los datos del Intent
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val posterPath = intent.getStringExtra("poster_path")

        // Asignamos los valores a los elementos de la UI
        titleTextView.text = title
        overviewTextView.text = overview

        // Usamos Glide para cargar la imagen del póster
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(posterImageView)
    }
}
