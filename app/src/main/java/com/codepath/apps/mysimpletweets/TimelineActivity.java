package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;

    private TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toast.makeText(this, "Successful Timeline Baby", Toast.LENGTH_SHORT).show();
        lvTweets = (ListView) findViewById(R.id.lvTweets);

        //Create array list
        tweets = new ArrayList<>();
        // Construct adaptor
        aTweets = new TweetsArrayAdapter(this, tweets);
        // Connect adaptor to list view
        lvTweets.setAdapter(aTweets);
        //Get the client
        client = TwitterApp.getRestClient(); //singleton client
        populateTimeline();
    }

    // Send PI request to get timeline json
    // fille the listview by creating the tweet objects from the json
    private void populateTimeline(){
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG SUCCESS", response.toString());
                //DESERIALIZE
                // CREATE MODELS
                //LOAD MODELS
                aTweets.addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG FAILURE", errorResponse.toString());
            }
        });
    }
}
