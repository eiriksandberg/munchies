package com.example.eiriksandberg.munchies.Domain;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.eiriksandberg.munchies.AsyncCallback;
import com.example.eiriksandberg.munchies.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eiriksandberg on 22.05.2017.
 */

public class PhotoTask extends AsyncTask<String, Void, AttributedPhoto> {

    private int mHeight;
    private int mWidth;
    private GoogleApiClient mGoogleApiClient;
    AsyncCallback<Bitmap> callback;

    public PhotoTask(int width, int height, GoogleApiClient mGoogleApiClient, AsyncCallback callback) {
        this.mHeight = height;
        this.mWidth = width;
        this.mGoogleApiClient = mGoogleApiClient;
        this.callback = callback;

    }

    /**
     * Loads the first photo for a place id from the Geo Data API.
     * The place id must be the first (and only) parameter.
     */
    @Override
    protected AttributedPhoto doInBackground(String... params) {
        if (params.length != 1) {
            return null;
        }
        final String placeId = params[0];
        AttributedPhoto attributedPhoto = null;

        PlacePhotoMetadataResult result = Places.GeoDataApi
                .getPlacePhotos(mGoogleApiClient, placeId).await();

        if (result.getStatus().isSuccess()) {
            PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();
            if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {
                // Get the first bitmap and its attributions.
                PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                CharSequence attribution = photo.getAttributions();
                // Load a scaled bitmap for this photo.
                Bitmap image = photo.getScaledPhoto(mGoogleApiClient, mWidth, mHeight).await()
                        .getBitmap();

                attributedPhoto = new AttributedPhoto(attribution, image);
            }
            // Release the PlacePhotoMetadataBuffer.
            photoMetadataBuffer.release();
        }
        return attributedPhoto;
    }

    @Override
    protected void onPostExecute(AttributedPhoto attributedPhoto) {
        if (attributedPhoto != null) {
            callback.successCallback(attributedPhoto.bitmap);
        } else {
            callback.errorCallback();
        }
    }
}