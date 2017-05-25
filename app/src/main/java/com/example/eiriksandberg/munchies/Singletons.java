package com.example.eiriksandberg.munchies;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class Singletons {
    private int radius;

    public Singletons(){
        this.radius = 5000;
    }

    public int getRadius(){
        if(radius != 0){
            return radius;
        } else {
            return new Singletons().getRadius();
        }
    }
}
