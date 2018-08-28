package com.example.ankit.placedetailstravel;


import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.example.ankit.placedetailstravel.Page1.placeId;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static final String APPNAME = "MainActivityJava";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public JSONObject resObj = new JSONObject();
    public JSONArray reviewsObj = new JSONArray();

    String formatted_address;
    String formatted_phone_number;
    Double rating;
    String googleUrl;
    String website;
    int price_level = 999;

        /**
         * The {@link ViewPager} that will host the section contents.
         */
    private ViewPager mViewPager;

    public  static class data{
        private String  datadetails;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJsonPlaceDetails();
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setOffscreenPageLimit(3);



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Instantiate the RequestQueue.
        // RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://letusenjoy-env.us-east-2.elasticbeanstalk.com/reviews?name=USC%20School%20of%20Cinematic%20Arts&city=Los%20Angeles&state=CA&country=US&address=900%20W%2034th%20St%2CLos%20Angeles%2CCA%2090007%2CUSA";

// Request a string response from the provided URL.
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String  text = response;
                        // Display the first 500 characters of the response string.
                        // mTextView.setText("Response is: "+ response);
                        Log.d(APPNAME,"VOLLEY RESPONSE "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Log.d(APPNAME,"VOLLEY ERROR GETMESSAGE"+error.getMessage());
                Log.d(APPNAME,"VOLLEY ERROR TOSTRING"+error.toString());

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

// Add the request to the RequestQueue.
        queue.add(stringRequest);*/


      /*  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("reviews");
                            for(int i = 0;i<jsonArray.length();i++){
                                JSONObject reviewInstance = jsonArray.getJSONObject(i);
                                String text = reviewInstance.getString("text");
                                int rating = reviewInstance.getInt("rating");
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
        queue.add(jsonObjectRequest);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getJsonPlaceDetails(){
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJM5pPyaHDyIARi2GWubblECA&key=AIzaSyBKPvdRoFqmuG5EBYcockzPBi_qFDwWsAM";
        String url ="https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key=AIzaSyBKPvdRoFqmuG5EBYcockzPBi_qFDwWsAM";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            resObj = response.getJSONObject("result");
                             formatted_address = resObj.getString("formatted_address");
                             formatted_phone_number = resObj.getString("formatted_phone_number");
                           //  rating = resObj.getDouble("rating");
                             googleUrl = resObj.getString("url");
                             website = resObj.getString("website");
                            if(resObj.has("price_level")){
                                price_level = resObj.getInt("price_level");
                                Log.d(APPNAME,"INFO"+price_level);
                            }
                            reviewsObj = resObj.getJSONArray("reviews");
                            //int price_level = resObj.getInt("price_level");

                            Log.d(APPNAME,"INFO"+formatted_address);
                            Log.d(APPNAME,"INFO"+formatted_phone_number);
                          //  Log.d(APPNAME,"INFO"+rating.toString());
                            Log.d(APPNAME,"INFO"+googleUrl);
                            Log.d(APPNAME,"INFO"+website);
                            //Log.d(APPNAME,"INFO"+price_level);


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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            Log.d(APPNAME,"position = "+position);
            switch(position)
            {

                case 0:
                    InfoTab tab1 = new InfoTab();
                    Bundle bundle = new Bundle();
                    bundle.putString("formatted_address", formatted_address);
                    bundle.putString("formatted_phone_number", formatted_phone_number);
                    bundle.putString("googleUrl", googleUrl);
                    bundle.putString("website", website);
                    bundle.putDouble("rating", 3.5);
                    bundle.putInt("price_level", price_level);


// set Fragmentclass Arguments

                    tab1.setArguments(bundle);
                    return tab1;
                case 1:
                    MapTab tab2 = new MapTab();
                    return  tab2;
                case 2:
                    PhotosTab tab3 = new PhotosTab();
                    return  tab3;
                case 3:
                    ReviewsTab tab4 = new ReviewsTab();
                    return  tab4;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
        @Override
        public  CharSequence getPageTitle(int position){
            switch (position){
                case 0 : return "INFO";
                case 1 : return "MAP";
                case 2 : return "PHOTOS";
                case 3 : return "REVIEWS";
            }
            return null;
        }
    }

}

/**
 * A placeholder fragment containing a simple view.
 */
  /*  public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */





