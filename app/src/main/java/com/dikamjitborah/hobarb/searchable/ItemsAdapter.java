package com.dikamjitborah.hobarb.searchable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> implements Filterable {

    ArrayList<ItemsBean> itemsBeanArrayList;
    ArrayList<ItemsBean> itemsBeanArrayList_all;


    ItemsAdapter(ArrayList<ItemsBean> itemsBeanArrayList) {
        this.itemsBeanArrayList = itemsBeanArrayList;
        itemsBeanArrayList_all = new ArrayList<>(itemsBeanArrayList);
    }


    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        ItemsViewHolder itemsViewHolder = new ItemsViewHolder(view);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {

        ItemsBean itemsBean = itemsBeanArrayList.get(position);

        holder.name.setText(itemsBean.getName());
        holder.surname.setText(itemsBean.getSurname());

    }

    @Override
    public int getItemCount() {
        return itemsBeanArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ItemsBean> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(itemsBeanArrayList_all);
            } else {
                String filter_pattern = charSequence.toString().toLowerCase().trim();

                for (ItemsBean itemsBean : itemsBeanArrayList_all) {
                    if (itemsBean.getName().toLowerCase().contains(filter_pattern)) {
                        filtered.add(itemsBean);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            itemsBeanArrayList.clear();
            itemsBeanArrayList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {

        TextView name, surname;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_items);
            surname = itemView.findViewById(R.id.surname_items);
        }
    }
}
