package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by gtrice on 8/27/16.
 */

// Taking Tweet objects and turn them into views
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    // TODO (gtrice): Implement View Holder Pattern!!!


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get tweet
        final Tweet tweet = getItem(position);

        // Find/inflate template
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }

        // Find subviews to fill
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        ImageView ivReply= (ImageView) convertView.findViewById(R.id.ivReplyIcon);
        ImageView ivRetweet = (ImageView) convertView.findViewById(R.id.ivRetweetIcon);
        final ImageView ivFavorite = (ImageView) convertView.findViewById(R.id.ivFavoriteIcon);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvUserHandle = (TextView) convertView.findViewById(R.id.tvUserHandle);
        TextView tvTimeAgo = (TextView) convertView.findViewById(R.id.tvTimeAgo);
        TextView tvRetweetCount = (TextView) convertView.findViewById(R.id.tvRetweetCount);
        TextView tvFavoritesCount = (TextView) convertView.findViewById(R.id.tvFavoriteCount);


        // Populate data into subviews
        tvUserName.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        tvUserHandle.setText(tweet.getUser().getScreenName());
        tvTimeAgo.setText(tweet.getRelativeCreatedAt());
        tvRetweetCount.setText(Long.toString(tweet.getRetweetCount()));
        tvFavoritesCount.setText(Long.toString(tweet.getFavoritesCount()));

        ivProfileImage.setImageResource(android.R.color.transparent); // clear out old image
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).transform(new RoundedCornersTransformation(4, 4)).into(ivProfileImage);

        ivFavorite.setImageResource(android.R.color.transparent);
        if (tweet.isFavorited()) {
            // change to filled star
            ivFavorite.setImageResource(R.drawable.ic_action_favorite_filled);
        } else {
            ivFavorite.setImageResource(R.drawable.ic_action_favorite_unfilled);
        }

        TwitterClient client = TwitterApp.getRestClient();
        ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // compose tweet
            }
        });


        ivRetweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make client request
            }
        });

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // figure out if it's already been liked
                TwitterClient client = TwitterApp.getRestClient();
                if (tweet.isFavorited()) {
                    client.unlikeTweet(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // Unfill star
                            ivFavorite.setImageResource(R.drawable.ic_action_favorite_unfilled);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            // Fill star
                            ivFavorite.setImageResource(R.drawable.ic_action_favorite_filled);
                        }
                    },tweet.getUid());
                } else {
                    client.likeTweet(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // fill star
                            ivFavorite.setImageResource(R.drawable.ic_action_favorite_filled);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            // Unfill star
                            ivFavorite.setImageResource(R.drawable.ic_action_favorite_unfilled);
                        }
                    }, tweet.getUid());
                }
            }
        });

        // Return the view for list
        return convertView;
    }
}
