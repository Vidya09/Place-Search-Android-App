package com.example.ankit.placedetailstravel;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import org.w3c.dom.Text;




import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.example.ankit.placedetailstravel.Page1.jsonResultsData;
import static com.example.ankit.placedetailstravel.Page1.lstSearch;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment implements OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView myrecyclesearch;
    //public static List<SearchModel> lstSearch;
    AutoCompleteTextView autocomplete;
    View v;
    private static final String TAG = "frontGUI";
    String[] arr = { "Paris,France", "PA,United States","Parana,Brazil",
            "Padua,Italy", "Pasadena,CA,United States"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        lstSearch = new ArrayList();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       v = inflater.inflate(R.layout.fragment_tab1, container, false);
       /* myrecyclesearch = (RecyclerView)view.findViewById(R.id.tabSearchlayout);
        SearchRecyclerViewAdapter  recycleAdapter = new SearchRecyclerViewAdapter(getContext(),MainActivity.lstSearch);
        myrecyclesearch.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyclesearch.setAdapter(recycleAdapter);*/


        /* //final JSONArray[] jsonData = new JSONArray[1];*/

        autocomplete = (AutoCompleteTextView)
                v.findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.select_dialog_item, arr);

        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter);



        // Spinner element
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerclass);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Default");
        categories.add("Airport");
        categories.add("Amusement Park");
        categories.add("Aquarium");
        categories.add("Art Gallery");
        categories.add("Bakery");
        categories.add("Bar");
        categories.add("Beauty Salon");
        categories.add("Bowling Alley");
        categories.add("Bus Station");
        categories.add("Cafe");
        categories.add("Campground");
        categories.add("Car Rental");
        categories.add("Casino");
        categories.add("Lodging");
        categories.add("Movie Theater");
        categories.add("Museum");
        categories.add("Night Club");
        categories.add("Park");
        categories.add("Parking");
        categories.add("Restaurant");
        categories.add("Shopping Mall");
        categories.add("Restaurant");
        categories.add("Stadium");
        categories.add("Subway Station");
        categories.add("Taxi Stand");
        categories.add("Train Station");
        categories.add("Transit Station");
        categories.add("Travel Agency");
        categories.add("Zoo");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Button button = (Button) v.findViewById(R.id.buttonSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        Button clearbutton = (Button) v.findViewById(R.id.clearbutton);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleardata();
            }
        });

        RadioButton radioHere = (RadioButton)v.findViewById(R.id.hereval) ;
        radioHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked();
            }
        });

        RadioButton locationVal = (RadioButton)v.findViewById(R.id.locationval);
        locationVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked();
            }
        });

      /*  Button btnOpen = (Button) v.findViewById(R.id.buttonSearch);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url ="http://webhw7sample-env.us-west-1.elasticbeanstalk.com/dictionary-api?address=%22california%22&keyword=%22cafe%22&distance=%2210%22";

// Request a string response from the provided URL.
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    MainActivity.jsonResultsData = response.getJSONArray("results");
                                    for(int i =0;i< jsonResultsData.length();i++){
                                        JSONObject searchInstance = jsonResultsData.getJSONObject(i);
                                        String icon = searchInstance.getString("icon");
                                        String name = searchInstance.getString("name");
                                        String description = searchInstance.getString("vicinity");
                                        String placeId = searchInstance.getString("place_id");
                                        lstSearch.add(new SearchModel(icon,name,description,placeId));
                                    }
                                    // Code here executes on main thread after user presses button
                                    Intent in = new Intent(getActivity(),SearchResults.class);
                                    //in.putExtra("JSON_DATA", lstSearch);
                                    startActivity(in);
                                    //jsonData[0] = jsonResultsData;
                                    //JSONObject test = jsonData;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);



            }
        });*/


        // Inflate the layout for this fragment
        return v;

    }

    @SuppressLint("Range")
    private void sendRequest() {

        EditText et11 = (EditText)v.findViewById(R.id.plain_text_input);
        EditText et1111=(EditText)v.findViewById(R.id.enterdist);
        autocomplete = (AutoCompleteTextView)
                v.findViewById(R.id.autoCompleteTextView1);
        TextView text1 = v.findViewById(R.id.keymisserr);
        TextView text2= v.findViewById(R.id.locmisserr);
        if(et11.getText().toString().isEmpty())
        {
            text1.setVisibility(View.VISIBLE);
            text1.setText("Please enter mandatory field");
        }else{
            text1.setVisibility(View.INVISIBLE);
        }

        EditText et111 = (EditText)v.findViewById(R.id.autoCompleteTextView1);
        RadioButton rd12= v.findViewById(R.id.locationval);
        if(rd12.isChecked())
        {
            if(et111.getText().toString().isEmpty())
            {
                text2.setVisibility(View.VISIBLE);
                text2.setText("Please enter mandatory field");
            }else{
                text2.setVisibility(View.INVISIBLE);
            }
        }

        if(!et11.getText().toString().isEmpty()) {
            if(rd12.isChecked() &&!et111.getText().toString().isEmpty() || !rd12.isChecked()) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                //String url = "http://webhw7sample-env.us-west-1.elasticbeanstalk.com/dictionary-api?address=%22california%22&keyword=%22cafe%22&distance=%2210%22";
                String url = "http://webhw7sample-env.us-west-1.elasticbeanstalk.com/dictionary-api?address="+autocomplete+"&keyword="+et11+"&distance="+et1111;
                String urlCh = url;

// Request a string response from the provided URL.
               JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray js = response.getJSONArray("results");
                                    for (int i = 0; i < js.length(); i++) {
                                        JSONObject searchInstance = js.getJSONObject(i);
                                        String icon = searchInstance.getString("icon");
                                        String name = searchInstance.getString("name");
                                        String description = searchInstance.getString("vicinity");
                                        String placeId = searchInstance.getString("place_id");
                                        lstSearch.add(new SearchModel(icon, name, description, placeId));
                                    }
                                    // Code here executes on main thread after user presses button
                                    Intent in = new Intent(getActivity(), SearchResults.class);
                                    //in.putExtra("JSON_DATA", lstSearch);
                                    startActivity(in);
                                    //jsonData[0] = jsonResultsData;
                                    //JSONObject test = jsonData;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                    }
                });

                //Change

               /* JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    JSONArray jsonResultsData = response.getJSONArray("result");
                                    for (int i = 0; i < jsonResultsData.length(); i++) {
                                        JSONObject searchInstance = jsonResultsData.getJSONObject(i);
                                        String icon = searchInstance.getString("icon");
                                        String name = searchInstance.getString("name");
                                        String description = searchInstance.getString("vicinity");
                                        String placeId = searchInstance.getString("place_id");
                                        lstSearch.add(new SearchModel(icon, name, description, placeId));
                                    }
                                    // Code here executes on main thread after user presses button
                                    Intent in = new Intent(getActivity(), SearchResults.class);
                                    //in.putExtra("JSON_DATA", lstSearch);
                                    startActivity(in);
                                    //jsonData[0] = jsonResultsData;
                                    //JSONObject test = jsonData;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                                error.printStackTrace();


                            }
                        });*/
                /*stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

                //Change

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        }


    }

    private void cleardata() {
        EditText et11 = (EditText)v.findViewById(R.id.plain_text_input);
        et11.setText("");
        EditText et111 = (EditText)v.findViewById(R.id.autoCompleteTextView1);
        RadioButton valuehere= v.findViewById(R.id.hereval);
        RadioButton rd12= v.findViewById(R.id.locationval);
        if(rd12.isChecked())
        {
            et111.setText("");
            valuehere.setChecked(true);

        }

        EditText et1111=(EditText)v.findViewById(R.id.enterdist);
        et1111.setText("");
        Spinner spinnrval = (Spinner) v.findViewById(R.id.spinnerclass);
        spinnrval.setSelection(0);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void callActivity(){
        Intent in = new Intent(getActivity(), SearchResults.class);
        //in.putExtra("JSON_DATA", lstSearch);
        startActivity(in);
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    private void onRadioButtonClicked() {
        // Is the button now checked?
        RadioButton valuehere= v.findViewById(R.id.hereval);
        RadioButton locvalue= v.findViewById(R.id.locationval);
        //boolean checked = ((RadioButton) v).isChecked();
        if(locvalue.isSelected()){
            locvalue.setChecked(true);
        }else if(valuehere.isSelected()){
            valuehere.setChecked(true);
        }
        // Check which radio button was clicked
       /* switch(v.getId()) {
            case R.id.hereval:
                if (checked)
                    valuehere.setChecked(true);
                break;
            case R.id.locationval:
                if (checked)
                    locvalue.setChecked(true);
                break;
        }*/
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* public void sendMessage(View view) {
        Intent intent = new Intent(this, SearchResults.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String message = "hello";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
