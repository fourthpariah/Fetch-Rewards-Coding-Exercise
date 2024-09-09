package com.example.fetchcodingexercise;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //RecyclerView itemRV = findViewById(R.id.itemRecyclerView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parseJSON();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void parseJSON() {
        Retrofit retrofitInstance = new retrofit2.Retrofit.Builder().
                baseUrl("https://fetch-hiring.s3.amazonaws.com").
                addConverterFactory(GsonConverterFactory.create()).
                build();

        ItemAPI service = retrofitInstance.create(ItemAPI.class);
        Call<List<ItemModel>> call = service.getAllItems();
        call.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                List<ItemModel> items = response.body();
                initRecyclerView(items);
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initRecyclerView(List<ItemModel> model) {
        RecyclerView itemRV = findViewById(R.id.itemRecyclerView);
        ItemAdaptor itemAdaptor = new ItemAdaptor(this, model);
        itemRV.setLayoutManager(new LinearLayoutManager(this));
        itemRV.setAdapter(itemAdaptor);
    }


}