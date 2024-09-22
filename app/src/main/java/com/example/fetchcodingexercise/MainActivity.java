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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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

    // This method just gets the JSON data and sends it to the display
    private void parseJSON() {
        Retrofit retrofitInstance = new Retrofit.Builder().
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

    // this method filters/sorts the json data and creates the display as nested recyclerviews
    private void initRecyclerView(List<ItemModel> model) {
        filterItems(model);
        sortItems(model);

        RecyclerView groupRV = findViewById(R.id.main_recycler_view);

        Map<Integer, List<ItemModel>> itemsPerID = model.stream().collect(Collectors.groupingBy(ItemModel::getItemListId));

        List<ListModel> itemLists = new ArrayList<>();
        for (Map.Entry<Integer, List<ItemModel>> group : itemsPerID.entrySet()) {
            ListModel newList = new ListModel();
            newList.setGroupId(group.getKey());
            newList.setItemList(group.getValue());
            itemLists.add(newList);
        }

        ListAdaptor listAdaptor = new ListAdaptor(itemLists);
        groupRV.setLayoutManager(new LinearLayoutManager(this));
        groupRV.setAdapter(listAdaptor);
    }

    public void filterItems(List<ItemModel> itemModelList) {
        itemModelList.removeIf(item -> item.getItemName() == null || Objects.equals(item.getItemName(), ""));

    }

    // NOTE
    // This sorting method sorts the set of items first by listId, then by name
    // since name is a string, they will be sorted as strings
    // EX: [..., "Item 28", "Item 280", "Item 29",...]
    // While this is not in numerical order, I will simply try to satisfy the directions as given
    public void sortItems(List<ItemModel> itemModelList) {
        itemModelList.sort(Comparator.comparing(ItemModel::getItemListId).thenComparing(ItemModel::getItemName));
    }

}