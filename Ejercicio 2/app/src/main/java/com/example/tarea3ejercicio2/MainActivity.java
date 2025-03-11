package com.example.tarea3ejercicio2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private TextView tvNoResults;
    private ProgressBar progressBar; // ProgressBar para mostrar el estado de carga
    private BookAdapter bookAdapter;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recyclerView);
        tvNoResults = findViewById(R.id.tvNoResults);
        progressBar = findViewById(R.id.progressBar); // Referencia al ProgressBar

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(bookAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    // Validar caracteres especiales
                    if (!query.matches("[a-zA-Z0-9 ]*")) {
                        Toast.makeText(MainActivity.this, "La búsqueda no puede contener caracteres especiales.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    searchBooks(query); // Llamar a la función para realizar la búsqueda
                }
            }
        });
    }

    private void searchBooks(String query) {
        // Mostrar el ProgressBar antes de realizar la búsqueda
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.searchBooks(query).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                // Ocultar el ProgressBar cuando recibimos la respuesta
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    bookList.clear();
                    bookList.addAll(response.body().getBooks());
                    bookAdapter.notifyDataSetChanged();

                    // Mostrar mensaje "Sin resultados" si la lista está vacía
                    if (bookList.isEmpty()) {
                        tvNoResults.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        tvNoResults.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                } else {
                    // Si la respuesta no es exitosa, también ocultamos el ProgressBar
                    progressBar.setVisibility(View.GONE);
                    Log.e("API_ERROR", "Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                // Ocultar el ProgressBar en caso de error
                progressBar.setVisibility(View.GONE);
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}


