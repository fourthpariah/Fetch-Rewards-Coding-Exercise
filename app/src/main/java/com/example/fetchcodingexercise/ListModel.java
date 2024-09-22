package com.example.fetchcodingexercise;

import java.util.List;

public class ListModel {
    private Integer groupId;
    private List<ItemModel> itemList;

    public Integer getGroupId() { return groupId; }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<ItemModel> getItemList() { return itemList; }

    public void setItemList(List<ItemModel> items) { this.itemList = items; }

}
