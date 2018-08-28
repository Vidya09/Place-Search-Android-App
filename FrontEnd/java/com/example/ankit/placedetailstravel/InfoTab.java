package com.example.ankit.placedetailstravel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class InfoTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String formatted_address;
    private String formatted_phone_number;
    private String googleUrl;
    private String website;
    private Double rating;
    private int price_level;

    private  JSONObject resObj;
    private String APPNAME = "InfoTab";
    private View v;

    //private OnFragmentInteractionListener mListener;

    public InfoTab() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoTab.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoTab newInstance(String param1, String param2) {
        InfoTab fragment = new InfoTab();
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
           formatted_address= getArguments().getString("formatted_address");
            formatted_phone_number = getArguments().getString("formatted_phone_number");
            googleUrl = getArguments().getString("googleUrl");
            website = getArguments().getString("website");
           // rating = getArguments().getDouble("rating");
            rating = 3.5;
            price_level = getArguments().getInt("price_level");

            Log.d(APPNAME,"INSIDE"+price_level);

        Log.d(APPNAME,"INSIDE"+formatted_address);
        Log.d(APPNAME,"INSIDE"+formatted_phone_number);
        Log.d(APPNAME,"INSIDE"+rating.toString());
        Log.d(APPNAME,"INSIDE"+googleUrl);
        Log.d(APPNAME,"INSIDE"+website);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_info_tab, container, false);
        TextView addr = v.findViewById(R.id.addressValue);
        addr.setText(formatted_address);
        TextView phone = v.findViewById(R.id.phoneNumber);
        phone.setText(formatted_phone_number);
       TextView price = v.findViewById(R.id.priceLevel);
       String pText;
       if(price_level == 999 || price_level == 0){
        pText = "";
       }
       else{
           pText="";
           for(int i = 0;i<price_level;i++){
               pText+="$";
           }
       }
        price.setText(pText);
        //Implement rate bar
        TextView googleWeb = v.findViewById(R.id.googlePage);
        googleWeb.setText(googleUrl);
        TextView web = v.findViewById(R.id.website);
        web.setText(website);
        RatingBar rb = (RatingBar) v.findViewById(R.id.ratingBarInfo);
       // rb.setNumStars(5);
        Double r = 3.5;
        rb.setRating(Float.parseFloat(r.toString()));
        return v;
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


}
