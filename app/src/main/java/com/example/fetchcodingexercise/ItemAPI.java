package com.example.fetchcodingexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemAPI {
    @GET("/hiring.json")
    Call<List<ItemModel>> getAllItems();
}
