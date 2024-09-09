package com.example.fetchcodingexercise;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemModel implements Serializable {
    @SerializedName("id")
    private Integer itemId;

    @SerializedName("listId")
    private Integer itemListId;

    @SerializedName("name")
    private String itemName;
}
