package com.example.eiriksandberg.munchies;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eiriksandberg.munchies.Adapter.SettingsAdapter;
import com.example.eiriksandberg.munchies.Adapter.SwipeCardAdapter;
import com.example.eiriksandberg.munchies.Domain.AttributedPhoto;
import com.example.eiriksandberg.munchies.Domain.PhotoTask;
import com.example.eiriksandberg.munchies.Domain.Place;
import com.example.eiriksandberg.munchies.Domain.PlacesTask;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Places;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    Singletons globals = new Singletons();
    private int radius = globals.getRadius();
    private int i;

    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        ButterKnife.inject(this);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        PlacesTask task = new PlacesTask(new AsyncCallback<ArrayList<Place>>() {
            @Override
            public void successCallback(final ArrayList<Place> parsedResult) {
                final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
                final SwipeCardAdapter swipeCardAdapter = new SwipeCardAdapter(getApplicationContext(), parsedResult);
                flingContainer.setAdapter(swipeCardAdapter);
                System.out.println("ID: ******* " + parsedResult.get(0).getId());
                flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                    @Override
                    public void removeFirstObjectInAdapter() {
                        // this is the simplest way to delete an object from the Adapter (/AdapterView)
                        Log.d("LIST", "removed object!");
                        parsedResult.remove(0);
                        PhotoTask photoTask = new PhotoTask(650, 420, mGoogleApiClient, new AsyncCallback<Bitmap>() {
                            @Override
                            public void successCallback(Bitmap bitmap) {
                                ImageView iv = (ImageView) findViewById(R.id.item_photo);
                                iv.setImageBitmap(bitmap);
                            }
                            @Override
                            public void errorCallback() {
                                System.out.println("Something went wrong in phototask");
                            }
                        });
                        swipeCardAdapter.notifyDataSetChanged();
                        if (!parsedResult.isEmpty()) {
                            photoTask.execute(parsedResult.get(0).getId());
                        }
                        swipeCardAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onLeftCardExit(Object dataObject) {
                        //Do something on the left!
                        //You also have access to the original object.
                        //If you want to use it just cast it (String) dataObject
                        Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightCardExit(Object dataObject) {
                        Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdapterAboutToEmpty(int itemsInAdapter) {
                        // Ask for more data here
                        /*
                        al.add("XML ".concat(String.valueOf(i)));
                        swipeCardAdapter.notifyDataSetChanged();
                        Log.d("LIST", "notified");
                        i++;
                        */
                    }

                    @Override
                    public void onScroll(float scrollProgressPercent) {
                        View view = flingContainer.getSelectedView();
                        view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                        view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                    }});

                PhotoTask photoTask = new PhotoTask(650, 420, mGoogleApiClient, new AsyncCallback<Bitmap>() {
                    @Override
                    public void successCallback(Bitmap bitmap) {
                        ImageView iv = (ImageView) findViewById(R.id.item_photo);
                        iv.setImageBitmap(bitmap);
                    }
                    @Override
                    public void errorCallback() {
                        System.out.println("Something went wrong in phototask");
                    }
                });
                swipeCardAdapter.notifyDataSetChanged();
                if (!parsedResult.isEmpty()) {
                    photoTask.execute(parsedResult.get(0).getId());
                }
            }
            @Override
            public void errorCallback() {
                System.out.println("Something went wrong: MainActivity()");
            }
        });
        task.execute(sbMethod().toString());
        final Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("onConnectionFailed was called from MainActivity");
    }

    private void requestPermissions() {
        int accessFineLocPerm = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int internetPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (accessFineLocPerm != PackageManager.PERMISSION_GRANTED || internetPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 1);
        }
    }
    public StringBuilder sbMethod() {

        //use your current location here
        double mLatitude = 34.4133292;
        double mLongitude = -119.8609718;

        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + mLatitude + "," + mLongitude);
        sb.append("&radius=5000");
        sb.append("&types=" + "restaurant");
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyA7HkZYfhP0KLI0EdLkDQnaFqQxfj6a7QM");

        Log.d("Map", "api: " + sb.toString());

        return sb;
    }

    public void setRadius(int r){
        radius = r;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }
}
