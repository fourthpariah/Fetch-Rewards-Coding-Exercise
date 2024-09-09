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

    public Integer getItemId() {
        return itemId;
    }

    public Integer getItemListId() {
        return itemListId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemListId(Integer itemListId) {
        this.itemListId = itemListId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
