package com.example.ankit.placedetailstravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import static com.example.ankit.placedetailstravel.Page1.placeId;

public class ReviewsTab extends Fragment implements OnItemSelectedListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public static final String APPNAME = "Tab4";
    View v;
    private RecyclerView myrecyclerview;
    private ArrayList<Review> lstReviews = new ArrayList<>();
    private ArrayList<Review> lstReviewsGoogle = new ArrayList<>();

    ReviewsRecyclerViewAdapter recyclerAdapter;

    //private OnFragmentInteractionListener mListener;

    public ReviewsTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewsTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewsTab newInstance(String param1, String param2) {
        ReviewsTab fragment = new ReviewsTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        // lstReviews.add(new Review("Ankitha","12-05-1993","sfhd asfshd awfhewuf iuahdieuf wafhnwx rhdwucnx ewurhcxwxur iweruxche",R.drawable.ic_launcher_background));
        //lstReviews.add(new Review("Akshaya","08-02-1988","tsdfcud ewriuefd sioaidof  soidfueibvb ipdsvfdj visdvcjdf dsviufdivfd",R.drawable.ic_launcher_background));
        //lstReviews.add(new Review("Arjun","08-02-1988","uregvfdj sadijffuid udsfahc s usdgfhyduvgfd oudsyfahc cs uoayfenc dsud",R.drawable.ic_launcher_background));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reviews_tab, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.reviews_recyclerview);
        recyclerAdapter = new ReviewsRecyclerViewAdapter(getContext());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);

        // Spinner element
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerRev);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Google reviews");
        categories.add("Yelp reviews");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //getJSONReviews();
        return v;

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        lstReviews.clear();
        if(item == "Google reviews") {

            getJsonGoogleReviews();

        }
        if(item == "Yelp reviews") {
         // getJSONReviews();
            recyclerAdapter.clear();
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void getJSONReviews(){
        //Get reviews

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://letusenjoy-env.us-east-2.elasticbeanstalk.com/reviews?name=USC%20School%20of%20Cinematic%20Arts&city=Los%20Angeles&state=CA&country=US&address=900%20W%2034th%20St%2CLos%20Angeles%2CCA%2090007%2CUSA";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("reviews");
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject reviewInstance = jsonArray.getJSONObject(i);
                                String text = reviewInstance.getString("text");
                                Double rating = reviewInstance.getDouble("rating");
                                String timeCreated = reviewInstance.getString("time_created");
                                JSONObject user = reviewInstance.getJSONObject("user");
                                String imageUrl = user.getString("image_url");
                                String name = user.getString("name");
                                Log.d(APPNAME,"**************************");
                                Log.d(APPNAME,"text = "+text);
                                Log.d(APPNAME,"rating = "+rating);
                                Log.d(APPNAME,"timeCreated = "+timeCreated);
                                Log.d(APPNAME,"imageUrl = "+imageUrl);
                                Log.d(APPNAME,"name = "+name);
                                Log.d(APPNAME,"**************************");
                                lstReviews.add(new Review(name,timeCreated,text,imageUrl,rating));
                                recyclerAdapter.setReviews(lstReviews);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(APPNAME,"**************************");
                        Log.d(APPNAME,"VOLLEY ERROR");
                        error.printStackTrace();
                        Log.d(APPNAME,"**************************");

                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

        //Get reviews end
    }
    public void getJsonGoogleReviews(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
       // String url ="https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJM5pPyaHDyIARi2GWubblECA&key=AIzaSyBKPvdRoFqmuG5EBYcockzPBi_qFDwWsAM";
        String url ="https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key=AIzaSyBKPvdRoFqmuG5EBYcockzPBi_qFDwWsAM";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject resObj = response.getJSONObject("result");
                            JSONArray reviewsArray = resObj.getJSONArray("reviews");
                            for(int i = 0;i<reviewsArray.length();i++) {
                                JSONObject reviewInstance = reviewsArray.getJSONObject(i);
                                String text = reviewInstance.getString("text");
                                Double rating = reviewInstance.getDouble("rating");
                                long timec = reviewInstance.getLong("time");
                                Log.d(APPNAME,"**GOOGLE REV ***  timecreated = "+timec);
                                String timeCreated = "DUMMY TIME";
                                String imageUrl = reviewInstance.getString("profile_photo_url");
                                String name = reviewInstance.getString("author_name");
                                lstReviews.add(new Review(name,timeCreated,text,imageUrl,rating));
                                recyclerAdapter.setReviews(lstReviews);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(APPNAME,"**************************");
                        Log.d(APPNAME,"VOLLEY ERROR");
                        error.printStackTrace();
                        Log.d(APPNAME,"**************************");

                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
    private void setGoogleRev(){
        recyclerAdapter.setReviews(lstReviewsGoogle);
    }
    private void setYelpRev(){
        recyclerAdapter.setReviews(lstReviews);
    }

    }
