package org.classapp.tidseries1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NowShowingAdapter extends RecyclerView.Adapter<NowShowingAdapter.MovieHolder> {

    private Context context;
    private List<NowPlayingMovie> movieList;


    public NowShowingAdapter(Context context, List<NowPlayingMovie> movies) {
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.now_showing_item, parent, false);
        return new MovieHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        NowPlayingMovie movie = movieList.get(position);
//        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());
        holder.desc.setText(movie.getOverview());
        holder.desc.setMovementMethod(new ScrollingMovementMethod());
        String imgUrl = "https://image.tmdb.org/t/p/original" + movie.getPoster_path();
        Glide.with(context).load(imgUrl).into(holder.poster);

        holder.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newsUrl = "https://www.majorcineplex.com/search?searchkeyword=" + movie.getTitle() + "&searchtype=movie%7Cnews%7C";
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView poster, news;
        TextView title, desc, rating;
        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super((itemView));

            news = itemView.findViewById(R.id.news_img);
            poster = itemView.findViewById(R.id.poster_nowplay);
            title = itemView.findViewById(R.id.title_nowplay);
            desc = itemView.findViewById(R.id.desc_nowplay);
//            rating = itemView.findViewById(R.id.rating);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}