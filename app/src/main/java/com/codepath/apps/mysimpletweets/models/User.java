package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gtrice on 8/27/16.
 */
public class User implements Serializable{
    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return "@" + screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public String getTagLine() {
        return tagLine;
    }

    //list attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;
    public long followersCount;
    public long followingCount;
    public String tagLine;


    //from json to user obj
    public static User fromJson(JSONObject json) {
        User u = new User();

        try {
            u.name = json.getString("name");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.uid = json.getLong("id");
            u.followersCount = json.getLong("followers_count");
            u.followingCount = json.getLong("friends_count");
            u.tagLine = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }
}