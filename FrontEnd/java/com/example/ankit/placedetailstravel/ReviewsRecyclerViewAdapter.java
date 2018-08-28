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
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<Review> mData;
    public static CircleImageView authPic;
    private static final String ADAPTERREVIEW = "Review adapter";

    public ReviewsRecyclerViewAdapter(Context mContext){
        this.mContext = mContext;

    }
    public void setReviews(ArrayList<Review> reviewList){
        mData = reviewList;
        // if(mData.size() == 10) {
        notifyItemRangeChanged(0, mData.size());
        // }
    }
    public void clear() {

        mData.clear();
        notifyItemRangeRemoved(0, mData.size());
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(ADAPTERREVIEW,"PhotoSRecyclerViewAdapter : inside oncreateviewholder");
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_review,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review rev = mData.get(position);
        Log.d(ADAPTERREVIEW,"PhotoSRecyclerViewAdapter onBindViewHolder photo pos = "+position);
       // holder.authPic.setImageResource(rev.getAuthor_pic());
        Picasso.get().load(rev.getAuthor_pic()).into(authPic);
        holder.authName.setText(rev.getAuthor_name());
        holder.timeCreated.setText(rev.getTime_created());
        holder.review_text.setText(rev.getReview_text());
        double tmp = rev.getRate();
        float f = (float)tmp;
        holder.rateBar.setRating(f);

    }

    @Override
    public int getItemCount() {
        //Log.e(ADAPTERPHOTO,"PhotoSRecyclerViewAdapter getItemCount = "+mData.size());
        return mData == null ? 0 :mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //private ImageView authPic;
        private TextView authName;
        private TextView timeCreated;
        private TextView review_text;
        private RatingBar rateBar;



        public MyViewHolder(View itemView){
            super(itemView);
            Log.e(ADAPTERREVIEW,"MyViewHolder");
            authPic = (CircleImageView) itemView.findViewById(R.id.authorPic);
            authName = (TextView)itemView.findViewById(R.id.author_name);
            timeCreated = (TextView)itemView.findViewById(R.id.time_created);
            review_text = (TextView)itemView.findViewById(R.id.review_text);
            rateBar = (RatingBar)itemView.findViewById(R.id.ratingBar);



        }
    }
}
