package com.cagatay.ozata.project;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPT extends AppCompatActivity {

    public static String  name, education,age;

    TextView namearea;
    TextView ageaarea;
    TextView educationarea;
    ImageView back;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();

        setContentView(R.layout.detail_pt);

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // find R
        namearea=findViewById(R.id.textView10);
        ageaarea=findViewById(R.id.textView12);
        educationarea=findViewById(R.id.textView11);
        back= findViewById(R.id.detail_back);

        // recieve data from intent
        Bundle bundle= getIntent().getExtras();
        name=bundle.getString("name");
        age = bundle.getString("age");
        education = bundle.getString("education");

        // set textview
        namearea.setText(name);
        ageaarea.setText(age);
        educationarea.setText(education);

        // Create database connection
        dbHelper=new DBHelper(this);
        dbHelper.open();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setFavorite(View view) {
        PersonalTrainerDB.insertContact(dbHelper, 0, name, education);
    }
}
