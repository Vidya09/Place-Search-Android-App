package com.example.ankit.placedetailstravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import static com.example.ankit.placedetailstravel.Page1.placeId;


public class PhotosTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;
    public static final String APPNAME = "Tab3";
    View v;
    private RecyclerView myrecyclerview;
    private ArrayList<Bitmap> lstPhotos = new ArrayList<>();
    PhotosRecyclerViewAdapter recyclerAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    public PhotosTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosTab.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotosTab newInstance(String param1, String param2) {
        PhotosTab fragment = new PhotosTab();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_photos_tab, container, false);
        mGeoDataClient = Places.getGeoDataClient(this.getContext(),null);
        v = inflater.inflate(R.layout.fragment_photos_tab, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.photos_recyclerview);
        recyclerAdapter = new PhotosRecyclerViewAdapter(getContext());

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);

        getPics();
        return v;
    }

    private void getPics(){

        //final String placeId = "ChIJa147K9HX3IAR-lwiGIQv9i4";
        //final String placeId = "ChIJM5pPyaHDyIARi2GWubblECA";
        Log.d(APPNAME,"4.Tab3 Inside getPlacePhotos");
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);
        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                Log.e(APPNAME,"5. Tab3 inside onComplete PlacePhotoMetadataResponse");
                List<PlacePhotoMetadata> photosDataList = new ArrayList<>();
                PlacePhotoMetadataResponse photos = task.getResult();
                PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
                Log.d(APPNAME, "6. Tab3 number of photos " + photoMetadataBuffer.getCount());


                for (PlacePhotoMetadata photoMetadata : photoMetadataBuffer) {
                    photosDataList.add(photoMetadata.freeze());
                }

                Log.d(APPNAME,"7.Tab3 photosDataList length = "+photosDataList.size());
                photoMetadataBuffer.release();

                for(int i = 0;i<photosDataList.size();i++){

                    Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photosDataList.get(i));
                    photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
                            PlacePhotoResponse photo = task.getResult();
                            Log.d(APPNAME, "8.Tab3 Getting photo response");
                            Bitmap bitmap = photo.getBitmap();
                            lstPhotos.add(bitmap);

                            Log.d(APPNAME,"9.Setting adapter inside onComplete , size = "+lstPhotos.size());
                            recyclerAdapter.setPhotos(lstPhotos);


                        }

                    });



                }

            }



        });
    }
}
