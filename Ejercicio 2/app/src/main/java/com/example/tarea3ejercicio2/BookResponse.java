package com.example.tarea3ejercicio2;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BookResponse {
    @SerializedName("docs")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }
}