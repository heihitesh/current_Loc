package com.itshiteshverma.current_loc;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AndroidGPSTrackingActivity extends Activity {

    Button btnShowLocation , showLOC;

    // GPSTracker class
    GPSTracker gps;
    Intent intent = null;
    Intent chooser = null;
    EditText Lat , Long;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_gpstracking);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        showLOC = (Button) findViewById(R.id.bloc);

        Lat = (EditText) findViewById(R.id.etLati);
        Long = (EditText) findViewById(R.id.etLong);



        showLOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intent = new Intent(android.content.Intent.ACTION_VIEW);

                String la = Lat.getText().toString();
                String lo = Long.getText().toString();

                intent.setData(Uri.parse("geo:"+lo+","+la));
                chooser=Intent.createChooser(intent,"Launch Maps");
                startActivity(chooser);
            }
        });


        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(AndroidGPSTrackingActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line


                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    String lat = String.valueOf(latitude);
                    String lon = String.valueOf(longitude);


                    Long.setText(lat);
                    Lat.setText(lon);


                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });



    }

  
}