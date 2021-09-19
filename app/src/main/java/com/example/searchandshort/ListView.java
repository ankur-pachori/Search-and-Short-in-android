package com.example.searchandshort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListView extends RecyclerView.Adapter<ListView.ListHolder> implements Filterable {
    private ArrayList<DBHelper> namelist = new ArrayList<>();
    private Context context;
    private ArrayList<DBHelper> backUp;

    public ListView(ArrayList<DBHelper> namelist,Context context) {
        this.namelist = namelist;
        this.context = context;
        backUp = new ArrayList<>(namelist);
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.list_view,parent,false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
    holder.textView.setText(namelist.get(position).getName());
    String s = String.valueOf(namelist.get(position).getNumber());
    holder.textView2.setText(s);
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<DBHelper> s = new ArrayList<>();
        if (constraint.toString().isEmpty()){
            s.addAll(backUp);
        }else {
            for (DBHelper i:backUp){
                if (i.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                    s.add(i);
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values=s;
        return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        namelist.clear();
        namelist.addAll((ArrayList<DBHelper>)results.values);
        notifyDataSetChanged();
        }
    };
    class ListHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textView2;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            textView =(TextView) itemView.findViewById(R.id.TxtListView);
            textView2 =(TextView) itemView.findViewById(R.id.TxtListView2);
        }
    }
}
