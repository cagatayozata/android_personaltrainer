package com.cagatay.ozata.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFavorites_RecylerAdapter extends RecyclerView.Adapter<ListFavorites_RecylerAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<PersonalTrainer> recyclerItemValues;

    public ListFavorites_RecylerAdapter(Context context, ArrayList<PersonalTrainer>values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.reccyler_db, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final PersonalTrainer sm = recyclerItemValues.get(i);
        myRecyclerViewItemHolder.tv.setText(sm.getName());
        myRecyclerViewItemHolder.tv2.setText(sm.getType());

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

     class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tv, tv2;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tv2=itemView.findViewById(R.id.tv22);
            parentLayout = itemView.findViewById(R.id.constLayout);
        }
    }

}
