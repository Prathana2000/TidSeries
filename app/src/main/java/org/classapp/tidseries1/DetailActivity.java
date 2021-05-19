package org.classapp.tidseries1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.poster_image);
        TextView rating_tv = findViewById(R.id.mRating);
        TextView title_tv = findViewById(R.id.mTitle);
        TextView overview_tv = findViewById(R.id.movervie_tv);
        ImageView trailer = findViewById(R.id.trailer_img);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster");
        String mOverView = bundle.getString("overview");
        String mtrailer = bundle.getString("trailer").trim();
        Double mRating = bundle.getDouble("rating");

        if(mtrailer.isEmpty()){
            trailer .setVisibility(View.GONE);
        }

        Glide.with(this).load(mPoster).into(imageView);
        rating_tv.setText(mRating.toString());
        title_tv.setText(mTitle);
        overview_tv.setText(mOverView);

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mtrailer)));
            }
        });
    }
}
