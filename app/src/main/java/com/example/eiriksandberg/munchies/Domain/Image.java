package com.example.eiriksandberg.munchies.Domain;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class Image {
    private double height;
    private double width;
    private String reference;

    public Image(double height, double width, String reference) {
        this.height = height;
        this.width = width;
        this.reference = reference;
    }

    public Image() {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
