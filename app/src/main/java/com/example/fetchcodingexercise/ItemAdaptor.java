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

    private final Context context;
    private final List<ItemModel> itemModelList;

    public ItemAdaptor(Context context, List<ItemModel> itemModelList) {
        this.context = context;
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
        holder.itemID.setText(model.getItemId().toString());
        holder.itemListID.setText(model.getItemListId().toString());
        holder.itemName.setText(model.getItemName());
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
