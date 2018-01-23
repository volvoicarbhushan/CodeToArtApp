package com.example.bhushan.codetoartapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by bhushan on 23/1/18.
 */

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {
    Context context;
    ArrayList<Movies> moviesArrayList;

    public AdapterMovies(Context context, ArrayList<Movies> moviesArrayList) {
        this.context = context;
        this.moviesArrayList = moviesArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lay_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Movies movies = moviesArrayList.get(position);

        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + movies.getImage()).into(holder.imageView);
        holder.title.setText(movies.getTitle());
        holder.date.setText(movies.getRdate());

        if (movies.getCategory().equals("false")) {

            holder.category.setText("(U/A)");
        } else {

            holder.category.setText("(A)");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("Data", movies);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, title, category;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageView);
            date = itemView.findViewById(R.id.txtDate);
            title = itemView.findViewById(R.id.txtTitle);
            category = itemView.findViewById(R.id.txtCategory);


        }
    }
}
