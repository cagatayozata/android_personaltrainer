package com.cagatay.ozata.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListPersonal_JSON extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListPersonal_RecylerAdapter mListPersonalRecylerAdapter;
    private ProgressDialog mProgressDialog;
    private RequestQueue mRequestQueue;
    private JSONArray personaltrainerss;
    private JSONObject trainersJSONObject;
    private ArrayList<HashMap<String, String>> mArrayList;
    public static final String INFO_TRAINERS = "trainers";
    public static final String INFO_NAME = "name";
    public static final String INFO_EDUCATION = "education";
    public static final String INFO_AGE = "age";
    public static final String INFO_IMAGE = "img";
    private static final String JSON_URL = "https://cagatayozata.com/project/personal_trainer.json";

    DBHelper dbHelper;
    Intent intent;
    PersonalTrainer pt;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding title bar using code
        getSupportActionBar().hide();

        setContentView(R.layout.list_personaltrainers);

        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // rcyler
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRequestQueue = Volley.newRequestQueue(this);
        back=findViewById(R.id.listpt_back);

        // DB connection
        dbHelper=new DBHelper(this);
        dbHelper.open();

        // back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onClick(View v) {

        mArrayList = new ArrayList<HashMap<String, String>>();
        mProgressDialog = new ProgressDialog(ListPersonal_JSON.this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest (
                Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        trainersJSONObject = response;

                        // Call to AsyncTask
                        new getPTs().execute();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result",
                        "ERROR JSONObject request" + error.toString());
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

    }

    private class getPTs extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                personaltrainerss = trainersJSONObject.getJSONArray(INFO_TRAINERS);

                for (int i = 0; i < personaltrainerss.length(); i++) {
                    JSONObject c = personaltrainerss.getJSONObject(i);

                    String name = c.getString(INFO_NAME);
                    String edu = c.getString(INFO_EDUCATION);
                    String age = c.getString(INFO_AGE);
                    String imgName = c.getString(INFO_IMAGE);

                    HashMap<String, String> ptss = new HashMap<String, String>();

                    ptss.put(INFO_NAME, name);
                    ptss.put(INFO_EDUCATION, edu);
                    ptss.put(INFO_AGE, age);
                    ptss.put(INFO_IMAGE, "https://cagatayozata.com/project/personaltrainersimage/" + imgName);

                    mArrayList.add(ptss);

                }
            }
            catch (JSONException ee) {
                ee.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            if (mArrayList != null) {
                mListPersonalRecylerAdapter = new ListPersonal_RecylerAdapter(ListPersonal_JSON.this, mArrayList);
                mRecyclerView.setAdapter(mListPersonalRecylerAdapter);
            }
            else
                Toast.makeText(ListPersonal_JSON.this, "Not Found", Toast.LENGTH_LONG).show();
        }
    }

    public void enterDetailPage(final String age, final String name, final String education){

        intent = new Intent(ListPersonal_JSON.this, DetailPT.class);
        intent.putExtra("name",name);
        intent.putExtra("education", education);
        intent.putExtra("age", " "+age);
        ListPersonal_JSON.this.startActivity(intent);

    }

}
