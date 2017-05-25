package com.example.eiriksandberg.munchies.Domain;

import android.os.AsyncTask;
        import android.util.Log;

import com.example.eiriksandberg.munchies.AsyncCallback;
import com.example.eiriksandberg.munchies.Mapper.Place_JSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
        import java.util.List;

class ParserTask extends AsyncTask<String, Integer, ArrayList<Place>> {
    JSONObject jObject;
    AsyncCallback<ArrayList<Place>> callback;

    public ParserTask(AsyncCallback callback){this.callback = callback;};

    // Invoked by execute() method of this object
    @Override
    protected ArrayList<Place> doInBackground(String... jsonData) {

        ArrayList<Place> places = null;
        Place_JSON placeJson = new Place_JSON();

        try {
            jObject = new JSONObject(jsonData[0]);

            places = placeJson.parse(jObject);

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return places;
    }

    @Override
    protected void onPostExecute(ArrayList<Place> parsedPlaces) {
        System.out.println("OnPostExecute in ParserTask called");
        if (parsedPlaces != null){
            callback.successCallback(parsedPlaces);
        } else {
            System.out.println("Error in onPostExecute in ParserTask");
            callback.errorCallback();
        }
    }
}