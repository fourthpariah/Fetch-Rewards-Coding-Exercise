package com.example.fetchcodingexercise;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ViewHolder> {
    private final List<ListModel> listModelList;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public ListAdaptor(List<ListModel> listModelList) {
        this.listModelList = listModelList;
    }

    @NonNull
    @Override
    public ListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdaptor.ViewHolder holder, int position) {
        ListModel model = listModelList.get(position);
        holder.groupListID.setText("ListID " + model.getGroupId().toString());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false);

        linearLayoutManager.setInitialPrefetchItemCount(model.getItemList().size());

        ItemAdaptor itemAdaptor = new ItemAdaptor(holder.itemView.getContext(), model.getItemList());
        holder.itemRecyclerView.setLayoutManager(linearLayoutManager);
        holder.itemRecyclerView.setAdapter(itemAdaptor);
        holder.itemRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView groupListID;
        RecyclerView itemRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupListID = itemView.findViewById(R.id.textViewListNumber);
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view);
        }
    }
}
