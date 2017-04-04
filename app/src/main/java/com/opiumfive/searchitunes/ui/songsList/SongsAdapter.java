package com.opiumfive.searchitunes.ui.songsList;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.opiumfive.searchitunes.R;
import com.opiumfive.searchitunes.model.Song;
import com.opiumfive.searchitunes.ui.songDetail.DetailActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ItemViewHolder>{

    private List<Song> results = new ArrayList<>();
    private Context mContext;

    public SongsAdapter(Context c) {
        mContext = c;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(v);
    }

    public void updateItems(List<Song> songs) {
        results.clear();
        results.addAll(songs);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int i) {
        final Song item = results.get(i);

        holder.artist.setText(item.getArtistName());
        holder.song.setText(item.getTrackName());
        Picasso.with(mContext).load(results.get(i).getArtworkUrl100()).into(holder.imageView);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("song", item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        CardView cv;
        TextView artist;
        TextView song;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            cv = (CardView) itemView.findViewById(R.id.cardView);
            artist = (TextView) itemView.findViewById(R.id.cv_artist_name);
            song = (TextView) itemView.findViewById(R.id.cv_song);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
