package com.cagatay.ozata.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ListPersonal_RecylerAdapter extends RecyclerView.Adapter<ListPersonal_RecylerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<HashMap<String, String>> mArrayList;

    ListPersonal_JSON listPersonalJSON;

    public ListPersonal_RecylerAdapter(Context mContext, ArrayList<HashMap<String, String>> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        listPersonalJSON = (ListPersonal_JSON)mContext;
    }

    // Each object of the ViewHolder will be created here
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = (View) mLayoutInflater.inflate(R.layout.reccyler_json, parent, false);
        ViewHolder mViewHolder = new ViewHolder(itemView);

        return mViewHolder;
    }

    // This method will be called to assign data to each row or cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        HashMap<String,String> book = mArrayList.get(position);
        String imageNameAddress = book.get(ListPersonal_JSON.INFO_IMAGE);
        final String namee;
        final String educationn;
        final String age;

        Picasso.get()
                .load(imageNameAddress)
                .into(holder.image);

        holder.name.setText(book.get(ListPersonal_JSON.INFO_NAME));
        holder.educate.setText(book.get(ListPersonal_JSON.INFO_EDUCATION));

        namee=book.get(ListPersonal_JSON.INFO_NAME);
        educationn=book.get(ListPersonal_JSON.INFO_EDUCATION);
        age = book.get(ListPersonal_JSON.INFO_AGE);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPersonalJSON.enterDetailPage(" "+age, namee, educationn);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, educate;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.limv);
            name = (TextView) itemView.findViewById(R.id.tv);
            educate = (TextView) itemView.findViewById(R.id.tv2);
            parentLayout = itemView.findViewById(R.id.constraintLayout);

        }
    }
}