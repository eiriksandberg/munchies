package com.example.eiriksandberg.munchies.Domain;

import android.graphics.Bitmap;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class Place {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String vicinity;
    private String reference;
    private boolean openNow;
    private double rating;
    private Image image;
    private int priceLevel;
    private Bitmap bitmap;

    public Place(String id, String name, double latitude, double longitude, String vicinity, String reference, boolean openNow, double rating, Image image, int priceLevel, Bitmap bitmap) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vicinity = vicinity;
        this.reference = reference;
        this.openNow = openNow;
        this.rating = rating;
        this.image = image;
        this.priceLevel = priceLevel;
        this.bitmap = bitmap;
    }

    public Place() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", vicinity='" + vicinity + '\'' +
                ", reference='" + reference + '\'' +
                ", openNow=" + openNow +
                ", rating=" + rating +
                ", image=" + image +
                ", priceLevel=" + priceLevel +
                ", bitmap=" + bitmap +
                '}';
    }
}
