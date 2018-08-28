package com.example.ankit.placedetailstravel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.List;
import static com.example.ankit.placedetailstravel.Page1.placeId;
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {

    Context scontext;
    List<SearchModel> sData;
    public static  ImageView imgphoto;

    public SearchRecyclerViewAdapter(Context scontext, List<SearchModel> sData) {
        this.scontext = scontext;
        this.sData = sData;
        int len = sData.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(scontext).inflate(R.layout.item_searchresult, parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        int pos = position;
         holder.tv_name.setText(sData.get(position).getName());
         holder.tv_description.setText(sData.get(position).getDescription());
         Picasso.get().load(sData.get(position).getPhoto()).into(imgphoto);
         //holder.imgphoto.setImageResource(sData.get(position).getPhoto());
       holder.linearLayoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeId =  sData.get(position).getPlaceId();

                Intent in = new Intent(scontext, MainActivity.class);
                //in.putExtra("JSON_DATA", lstSearch);
                scontext.startActivity(in);


            }
        });
    }

    @Override
    public int getItemCount() {

        return sData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_description;
        //private ImageView imgphoto;
        public LinearLayout linearLayoutSearch;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.searchname);
            tv_description = (TextView)itemView.findViewById(R.id.searchndescription);
            imgphoto = (ImageView)itemView.findViewById(R.id.searchresd);
            linearLayoutSearch = (LinearLayout)itemView.findViewById(R.id.searchClick);
        }
    }
}
