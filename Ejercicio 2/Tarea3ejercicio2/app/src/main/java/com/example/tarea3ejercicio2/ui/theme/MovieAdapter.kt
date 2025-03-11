package com.example.tarea3ejercicio2.ui.theme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tarea3ejercicio2.R
import com.example.tarea3ejercicio2.model.Movie
import com.example.tarea3ejercicio2.ui.theme.MovieDetailActivity // Corregido: importación de la clase

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movie_title)
        val overview: TextView = view.findViewById(R.id.movie_overview)
        val poster: ImageView = view.findViewById(R.id.movie_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.poster)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java).apply {
                putExtra("title", movie.title)
                putExtra("overview", movie.overview)
                putExtra("poster_path", movie.posterPath)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movies.size
}
