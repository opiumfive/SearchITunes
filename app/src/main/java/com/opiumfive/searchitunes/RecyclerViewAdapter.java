package com.opiumfive.searchitunes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by allsw on 14.07.2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>{

    List<Result> results;
    Context mContext;

    public RecyclerViewAdapter(Context c, List<Result> r) {
        mContext = c;
        results = r;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ItemViewHolder cvh = new ItemViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int i) {
        holder.artist.setText(results.get(i).getArtistName().toString());

        holder.song.setText(results.get(i).getTrackName().toString());

        Picasso.with(mContext).load(results.get(i).getArtworkUrl100()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("artwork_url", results.get(i).getArtworkUrl100());
                intent.putExtra("audio_url", results.get(i).getPreviewUrl());
                intent.putExtra("artist", results.get(i).getArtistName());
                intent.putExtra("song", results.get(i).getTrackName());
                intent.putExtra("song_length", results.get(i).getTrackTimeMillis().toString());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView artist;
        TextView song;
        ImageView imageView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);

            artist = (TextView) itemView.findViewById(R.id.cv_artist_name);

            song = (TextView) itemView.findViewById(R.id.cv_song);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
