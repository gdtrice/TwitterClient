package com.codepath.apps.mysimpletweets.models;

import com.codepath.apps.mysimpletweets.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gtrice on 8/27/16.
 */

// Parse the JSON + store date, encapsulate state logic or display logic
public class Tweet {
    private String body;
    private long uid; // unique id for tweet

    public String getCreatedAt() {
        return createdAt;
    }

    public String getRelativeCreatedAt() {return Utilities.getRelativeTimeAgo(createdAt);}

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    private User user;
    private String createdAt;

    //Deserialize json
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if(tweet != null) {
                 tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue; // Wat?? from video timestamp (1:07:00) http://courses.codepath.com/course_videos/intro_to_android/vimeo/70911522?title=Simple+Twitter+Video+Walkthrough?????
            }
        }
        return tweets;
    }
}
