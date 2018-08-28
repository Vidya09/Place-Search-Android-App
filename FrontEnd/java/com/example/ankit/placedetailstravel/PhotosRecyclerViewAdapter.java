package com.example.ankit.placedetailstravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bitmap> mData;
    private static final String ADAPTERPHOTO = "photo adapter";

    public PhotosRecyclerViewAdapter(Context mContext){
        this.mContext = mContext;

    }
    public void setPhotos(ArrayList<Bitmap> photoList){
        mData = photoList;
       // if(mData.size() == 10) {
            notifyItemRangeChanged(0, mData.size());
       // }

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(ADAPTERPHOTO,"PhotoSRecyclerViewAdapter : inside oncreateviewholder");
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_photo,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bitmap photo = mData.get(position);
        Log.d(ADAPTERPHOTO,"PhotoSRecyclerViewAdapter onBindViewHolder photo pos = "+position);
        holder.img.setImageBitmap(photo);
    }

    @Override
    public int getItemCount() {
        //Log.e(ADAPTERPHOTO,"PhotoSRecyclerViewAdapter getItemCount = "+mData.size());
        return mData == null ? 0 :mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;


        public MyViewHolder(View itemView){
            super(itemView);
            Log.e(ADAPTERPHOTO,"MyViewHolder");
            img = (ImageView)itemView.findViewById(R.id.photo1);

        }
    }
}

