package com.example.evan.trailer_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/9/2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{


    Context context;
    List<MovieData> _movieList = new ArrayList<>();
    LayoutInflater inflater;
    Listener listener;

    public ListAdapter(Context context, List<MovieData> movieList){

        this.context = context;
        this._movieList = movieList;
        this.listener = (Listener) context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.movie_item, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.movie_thumb.setTag(position);
        holder.movie_name.setText(_movieList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return _movieList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView movie_name;
        ImageView movie_thumb;

        public ListViewHolder(View itemView) {
            super(itemView);

            movie_name = (TextView) itemView.findViewById(R.id.movie_name);
            movie_thumb= (ImageView) itemView.findViewById(R.id.movie_thumb);

        }
    }

}
