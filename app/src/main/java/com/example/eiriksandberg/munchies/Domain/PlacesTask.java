package com.example.eiriksandberg.munchies.Domain;

import android.os.AsyncTask;
import android.util.Log;

import com.example.eiriksandberg.munchies.AsyncCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class PlacesTask extends AsyncTask<String, Integer, String> {
    String data = null;
    AsyncCallback<ArrayList<Place>> callback;
    public PlacesTask(AsyncCallback callback){this.callback = callback;}

    // Invoked by execute() method of this object
    @Override
    protected String doInBackground(String... url) {
        try {
            data = downloadUrl(url[0]);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        return data;
    }

    // Executed after the complete execution of doInBackground() method
    @Override
    protected void onPostExecute(String result) {
        // Start parsing the Google places in JSON format
        // Invokes the "doInBackground()" method of the class ParserTask
        final ParserTask parserTask = new ParserTask(new AsyncCallback<ArrayList<Place>>() {
            @Override
            public void successCallback(ArrayList<Place> parsedResult) {
                callback.successCallback(parsedResult);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });
        parserTask.execute(result);
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while dl url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}