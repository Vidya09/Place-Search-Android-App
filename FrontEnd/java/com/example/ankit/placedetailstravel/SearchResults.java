package com.example.ankit.placedetailstravel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import java.util.List;

import static com.example.ankit.placedetailstravel.Page1.jsonResultsData;
import static com.example.ankit.placedetailstravel.Page1.lstSearch;

public class SearchResults extends AppCompatActivity{

    View v;
    private RecyclerView myrecyclesearch;
    //private List<SearchModel> lstSearch;
    private  JSONArray jsonResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myrecyclesearch = (RecyclerView)findViewById(R.id.tabSearchlayout);
        int len = lstSearch.size();
        SearchRecyclerViewAdapter  recycleAdapter = new SearchRecyclerViewAdapter(this,lstSearch);
        myrecyclesearch.setLayoutManager(new LinearLayoutManager(this));
        myrecyclesearch.setAdapter(recycleAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle value = extras;
            //The key argument here must match that used in the other activity
        }

        //try {
          /*  jsonResults = jsonResultsData;
            for(int i =0;i< jsonResultsData.length();i++){
                JSONObject searchInstance = null;
                try {
                    searchInstance = jsonResultsData.getJSONObject(i);
                    String icon = searchInstance.getString("icon");
                    String name = searchInstance.getString("name");
                    String description = searchInstance.getString("vicinity");
                    MainActivity.lstSearch.add(new SearchModel(icon,name,description));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }*/
            //jsonData[0] = jsonResultsData;
            //JSONObject test = jsonData;
        /*} catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

}
