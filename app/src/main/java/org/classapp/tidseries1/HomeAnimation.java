package org.classapp.tidseries1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeAnimation extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeseries);

        recyclerView = findViewById(R.id.loopinfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        movieList = new ArrayList<>();


        fetchMovies();

    }

    private void fetchMovies() {
        String url = "https://www.json-generator.com/api/json/get/cghiDVzGMi?indent=2";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0 ; i < response.length() ; i ++){
                    try {
                        String trailer, title, overview, poster;
                        Double rating;

                        JSONObject jsonObject = response.getJSONObject(i);

                        if (jsonObject.has("title")) {
                            title = jsonObject.getString("title");
                        } else {
                            title = "";
                        }

                        if (jsonObject.has("overview")) {
                            overview = jsonObject.getString("overview");
                        } else {
                            overview = "";
                        }

                        if (jsonObject.has("poster")) {
                            poster = jsonObject.getString("poster");
                        } else {
                            poster = "";
                        }

                        if (jsonObject.has("trailer")) {
                            trailer = jsonObject.getString("trailer");
                        } else {
                            trailer = "";
                        }

                        if (jsonObject.has("rating")) {
                            rating = jsonObject.getDouble("rating");
                        } else {
                            rating = 0.00;
                        }

                        Movie movie = new Movie(title, poster, overview, trailer, rating);
                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MovieAdapter adapter = new MovieAdapter(HomeAnimation.this, movieList);

                    recyclerView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeAnimation.this, "", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}