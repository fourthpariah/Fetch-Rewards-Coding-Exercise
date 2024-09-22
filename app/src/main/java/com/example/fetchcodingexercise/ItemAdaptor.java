package com.example.fetchcodingexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.ViewHolder> {

    private final List<ItemModel> itemModelList;

    public ItemAdaptor(Context context, List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdaptor.ViewHolder holder, int position) {
        ItemModel model = itemModelList.get(position);

        String idString = "ID: " + model.getItemId().toString();
        String listIdString = "List: " + model.getItemListId().toString();
        String itemNameString = "Name: " + model.getItemName();
        holder.itemID.setText(idString);
        holder.itemListID.setText(listIdString);
        holder.itemName.setText(itemNameString);
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemID;
        private final TextView itemListID;
        private final TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemID = itemView.findViewById(R.id.textViewID);
            itemListID = itemView.findViewById(R.id.textViewListId);
            itemName = itemView.findViewById(R.id.textViewName);
        }
    }

}
