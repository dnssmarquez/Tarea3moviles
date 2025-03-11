package com.example.tarea3ejercicio2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivCover;
    private TextView tvTitle, tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail); // Asegurar que usa el layout correcto

        // Referencias a los elementos de la UI
        ivCover = findViewById(R.id.ivCover);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);

        // Obtener datos del intent de forma segura
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            String coverUrl = intent.getStringExtra("coverUrl");

            // Asignar datos a los elementos de la UI con validación de nulos
            tvTitle.setText(title != null ? title : "Título no disponible");
            tvAuthor.setText(author != null ? author : "Autor desconocido");

            if (coverUrl != null && !coverUrl.isEmpty()) {
                Picasso.get().load(coverUrl).into(ivCover);
            } else {
                ivCover.setImageResource(R.drawable.placeholder);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Configurar ActionBar de manera segura
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Manejar el botón de regresar en la barra de acción
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
