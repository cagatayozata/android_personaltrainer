package com.cagatay.ozata.project;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ListFavorites_DB extends AppCompatActivity {

    private RecyclerView ryclerDB;
    private List<PersonalTrainer> recyclervalues;

    LinearLayoutManager layoutManager;
    ListFavorites_RecylerAdapter adapter;
    DBHelper dbHelper;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding title bar using code
        getSupportActionBar().hide();

        setContentView(R.layout.list_myfavs);

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create database connection
        dbHelper=new DBHelper(this);
        dbHelper.open();

        // recycler
        ryclerDB = findViewById(R.id.recyclerCountry);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ryclerDB.setLayoutManager(layoutManager);
        ryclerDB.hasFixedSize();

        // data from database to rycler
        recyclervalues=PersonalTrainerDB.getAllContact(dbHelper);
        adapter = new ListFavorites_RecylerAdapter(this, (ArrayList<PersonalTrainer>) recyclervalues);
        ryclerDB.setAdapter(adapter);

        // back button
        back=findViewById(R.id.listfav_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
