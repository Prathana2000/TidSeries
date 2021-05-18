package org.classapp.tidseries1;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NowShowingFragment extends Fragment {

    private NowShowingViewModel mViewModel;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<NowPlayingMovie> movieList;

    public static NowShowingFragment newInstance() {
        return new NowShowingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.now_showing_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NowShowingViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.loopinfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        movieList = new ArrayList<>();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) getView().findViewById(R.id.loopinfo);
        myList.setLayoutManager(layoutManager);

        fetchMovies();
    }

    private void fetchMovies() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=17c4f7802bad21d22a6180cc27d69eb8&language=th-TH&region=TH";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray nowshowList = response.getJSONArray("results");

                            for (int i = 0; i < nowshowList.length(); i++) {
                                JSONObject jsonObject = nowshowList.getJSONObject(i);
                                String overview = jsonObject.getString("overview");
                                String poster_path = jsonObject.getString("poster_path");
                                String release_date = jsonObject.getString("release_date");
                                String title = jsonObject.getString("title");
                                Double vote_average = jsonObject.getDouble("vote_average");

                                NowPlayingMovie movie = new NowPlayingMovie(overview, poster_path, release_date, title, vote_average);
                                movieList.add(movie);
                                NowShowingAdapter adapter = new NowShowingAdapter(getContext(), movieList);

                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "data invalid format", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "something wrong", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

}