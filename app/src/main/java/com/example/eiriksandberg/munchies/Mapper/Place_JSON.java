package com.example.eiriksandberg.munchies.Mapper;

import com.example.eiriksandberg.munchies.Domain.Image;
import com.example.eiriksandberg.munchies.Domain.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eiriksandberg on 13.05.2017.
 */
    public class Place_JSON {

        /**
         * Receives a JSONObject and returns a list
         */
        public ArrayList<Place> parse(JSONObject jObject) {

            JSONArray jPlaces = null;
            try {
                /** Retrieves all the elements in the 'places' array */
                jPlaces = jObject.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /** Invoking getPlaces with the array of json object
             * where each json object represent a place
             */
            return getPlaces(jPlaces);
        }

        private ArrayList<Place> getPlaces(JSONArray jPlaces) {
            int placesCount = jPlaces.length();
            ArrayList<Place> placesList = new ArrayList<Place>();
            Place place = null;

            /** Taking each place, parses and adds to list object */
            for (int i = 0; i < placesCount; i++) {
                try {
                    /** Call getPlace with place JSON object to parse the place */
                    place = getPlace((JSONObject) jPlaces.get(i));
                    placesList.add(place);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return placesList;
        }

    /**
     * Parsing the Place JSON object
     */
    private Place getPlace(JSONObject jPlace) {
        Place place = new Place();
        String placeName = "";
        String vicinity = "";
        String latitude = "";
        String longitude = "";
        String reference = "";
        Image image = new Image();
        double rating = 0;
        boolean openNow = false;
        int priceLevel = -1;

        try {
            // Extracting Place name, if available
            if (!jPlace.isNull("name")) {
                placeName = jPlace.getString("name");
            }

            if(!jPlace.isNull("id")) {
                place.setId(jPlace.getString("place_id"));
            }

            // Extracting Place Vicinity, if available
            if (!jPlace.isNull("vicinity")) {
                vicinity = jPlace.getString("vicinity");
            }

            if (!jPlace.isNull("photos")) {
                image.setHeight(jPlace.getJSONArray("photos").getJSONObject(0).getDouble("height"));
                image.setWidth(jPlace.getJSONArray("photos").getJSONObject(0).getDouble("width"));
                image.setReference(jPlace.getJSONArray("photos").getJSONObject(0).getString("photo_reference"));
                place.setImage(image);
            }

            if (!jPlace.isNull("rating")) {
                rating = jPlace.getDouble("rating");
                place.setRating(rating);
            }

            if (jPlace.isNull("opening_hours")){
                openNow = jPlace.getJSONObject("opening_hours").getBoolean("open_now");
                place.setOpenNow(openNow);
            }

            if (!jPlace.isNull("price_level")) {
                priceLevel = jPlace.getInt("price_level");
                place.setPriceLevel(priceLevel);
            }

            latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = jPlace.getString("reference");

            if (placeName != ""){
                place.setName(placeName);
            }
            if (latitude != ""){
                place.setLatitude(Double.parseDouble(latitude));
            }
            if(longitude != null){
                place.setLongitude(Double.parseDouble(longitude));
            }
            if (vicinity != null){
                place.setVicinity(vicinity);
            }
            if (reference != null) {
                place.setReference(reference);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
    }
