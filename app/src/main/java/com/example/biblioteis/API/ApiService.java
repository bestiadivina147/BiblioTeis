package com.example.biblioteis.API;

import com.example.biblioteis.models.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("books")
    Call<List<Libro>> getAllBooks();

}
