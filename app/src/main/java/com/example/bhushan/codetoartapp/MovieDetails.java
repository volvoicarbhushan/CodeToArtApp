package com.example.bhushan.codetoartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MovieDetails extends AppCompatActivity {

    Movies movies;
    TextView title, overview;
    RatingBar ratingBar;
    SliderLayout sliderLayout;
    RequestQueue requestQueue;
    Map<String, String> urlImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        requestQueue = Volley.newRequestQueue(MovieDetails.this);

        movies = (Movies) intent.getSerializableExtra("Data");

        sliderLayout = (SliderLayout) findViewById(R.id.SliderLayout);
        title = (TextView) findViewById(R.id.txtViewTitle);
        overview = (TextView) findViewById(R.id.txtViewOverView);
        ratingBar = (RatingBar) findViewById(R.id.RatingBar);

        title.setText("Title : " + movies.getTitle());
        overview.setText("OverView : " + "\n" + movies.getOverview());
        ratingBar.setRating(Float.parseFloat(movies.getRating()));


        urlImg = new HashMap<String, String>();


        String id = movies.getId();
        Log.d("TAG", "ID :" + id);

        String url = "https://api.themoviedb.org/3/movie/" + id + "/images?api_key=b7cd3340a794e5a2f35e3abb820b497f";
        Log.d("TAG", "URL :" + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response != null) {
                    parseData(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieDetails.this, "Error while fetching Data", Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(jsonObjectRequest);


    }


    void parseData(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("backdrops");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String image_path = "http://image.tmdb.org/t/p/w342" + jsonObject.getString("file_path");
                Log.d("TAG", image_path);

                urlImg.put("image" + i, image_path);

                int j = 0;

                for (int k = 0; k < jsonArray.length(); k++) {

                    TextSliderView textSliderView = new TextSliderView(this);
                    if (j < 5) {
                        textSliderView.description("img" + k).image(urlImg.get("img" + k)).setScaleType(BaseSliderView.ScaleType.Fit);
                        sliderLayout.addSlider(textSliderView);
                    }
                    j++;

                }

            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setDuration(9000);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    }

