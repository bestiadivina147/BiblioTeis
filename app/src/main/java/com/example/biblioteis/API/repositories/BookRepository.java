package com.example.biblioteis.API.repositories;

import com.example.biblioteis.API.ApiClient;
import com.example.biblioteis.API.ApiService;

public class BookRepository {

    private ApiService apiService;

    public BookRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }


}
