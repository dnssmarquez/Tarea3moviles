package com.example.tarea3ejercicio2;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Book {
    @SerializedName("title")
    private String title;

    @SerializedName("author_name")
    private List<String> authors;

    @SerializedName("cover_i")
    private int coverId;

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return (authors != null && !authors.isEmpty()) ? authors.get(0) : "Autor desconocido";
    }

    public String getCoverUrl() {
        return coverId > 0 ? "https://covers.openlibrary.org/b/id/" + coverId + "-M.jpg" : null;
    }
}
