package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gtrice on 8/27/16.
 */
public class User {
    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    //list attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    //from json to user obj
    public static User fromJson(JSONObject json) {
        User u = new User();

        try {
            u.name = json.getString("name");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.uid = json.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }
}
