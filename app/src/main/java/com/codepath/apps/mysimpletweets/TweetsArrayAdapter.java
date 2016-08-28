package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

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
        Tweet tweet = getItem(position);

        // Find/inflate template
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }

        // Find subviews to fill
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvUserHandle = (TextView) convertView.findViewById(R.id.tvUserHandle);
        TextView tvTimeAgo = (TextView) convertView.findViewById(R.id.tvTimeAgo);
        // Populate data into subviews
        tvUserName.setText(tweet.getUser().getName());
        tvBody.setText(tweet.getBody());
        tvUserHandle.setText(tweet.getUser().getScreenName());
        tvTimeAgo.setText(tweet.getRelativeCreatedAt());

        ivProfileImage.setImageResource(android.R.color.transparent); // clear out old image
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).transform(new RoundedCornersTransformation(4, 4)).into(ivProfileImage);
        // Return the view for list
        return convertView;
    }
}
