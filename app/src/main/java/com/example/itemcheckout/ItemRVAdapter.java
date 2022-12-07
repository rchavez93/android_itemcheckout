package com.example.itemcheckout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ItemDetailHostActivity> itemModalArrayList;
    private Context context;

    // constructor
    public ItemRVAdapter(ArrayList<ItemDetailHostActivity> itemModalArrayList, Context context) {
        this.itemModalArrayList = itemModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ItemDetailHostActivity modal = itemModalArrayList.get(position);
        holder.itemNameTV.setText(modal.getItemName());
        holder.itemDescTV.setText("'"+modal.getItemDescription()+"'");
        holder.itemCostTV.setText("$ "+modal.getItemCost());
        holder.itemStockTV.setText("Est. Stock: "+modal.getItemStock());
        holder.itemIDTV.setText("ID# "+String.valueOf(modal.getItemID()));
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return itemModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView itemNameTV, itemDescTV, itemCostTV, itemStockTV, itemIDTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            itemDescTV = itemView.findViewById(R.id.idTVItemDescription);
            itemCostTV = itemView.findViewById(R.id.idTVItemCost);
            itemStockTV = itemView.findViewById(R.id.idTVItemStock);
            itemIDTV = itemView.findViewById(R.id.idTVItemID);
        }
    }
}