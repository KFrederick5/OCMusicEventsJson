package edu.orangecoastcollege.cs273.kfrederick5.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    /**
     * Created three private view variables to relate to the coinciding
     * xml view
     */
    private ImageView eventImageView;
    private TextView eventTitleTextView;
    private TextView eventDateDayTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;
    private TextView eventTimeTextView;

    //In order to use AssetManager, need to know context
    private Context context = (Context) this;

    /**
     * Creates a view after one of the list items have been clicked and
     * displays the details of the item selected.
     * @param savedInstanceState calls for any recent saved information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_layout);

        /**
         * Received the intent from the EventListActivity and assigned them
         * to string variable values
         */
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String date = detailsIntent.getStringExtra("Date");
        String day = detailsIntent.getStringExtra("Day");
        String time = detailsIntent.getStringExtra("Time");
        String location = detailsIntent.getStringExtra("Location");
        String address1 = detailsIntent.getStringExtra("Address1");
        String address2 = detailsIntent.getStringExtra("Address2");
        String imageFileName = title.replace(" ","") + ".jpeg";

        /**
         * Hooked this controllers variables to the xml views.
         */
        eventImageView = (ImageView) findViewById(R.id.eventImageView);
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDayTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        //Load image from Assets folder using AssetManager class
        AssetManager am = context.getAssets();



        //Try to load image file
        try
        {
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);

        }
        catch (IOException ex)
        {
            Log.e("OC Music Events", "Cannot load image: " + imageFileName + ex);
        }

        eventTitleTextView.setText(title);
        eventDateDayTextView.setText(day + " - " + date);
        eventTimeTextView.setText(time);
        eventLocationTextView.setText(location);
        eventAddress1TextView.setText(address1);
        eventAddress2TextView.setText(address2);

    }
}
