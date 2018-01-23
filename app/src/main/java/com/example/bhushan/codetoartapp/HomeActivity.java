package com.example.bhushan.codetoartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterMovies adapterMovies;
    Movies movies;
    RequestQueue requestQueue;
    ArrayList<Movies> moviesArrayList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        requestQueue = Volley.newRequestQueue(this);

        loadData();

    }

    private void loadData()
    {


        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                moviesArrayList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String image = jsonObject.getString("poster_path");
                        String category = jsonObject.getString("adult");
                        String overview = jsonObject.getString("overview");
                        String date = jsonObject.getString("release_date");
                        String title = jsonObject.getString("title");
                        String rating = jsonObject.getString("vote_average");

                        movies = new Movies(id,date, category, overview, image, title, rating);
                        moviesArrayList.add(movies);
                        adapterMovies = new AdapterMovies(HomeActivity.this, moviesArrayList);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ;

                }

                recyclerView.setAdapter(adapterMovies);
                adapterMovies.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HomeActivity.this, "Error while fetching Data", Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(jsonObjectRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.info){
            Intent intent=new Intent(this,InfoActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
