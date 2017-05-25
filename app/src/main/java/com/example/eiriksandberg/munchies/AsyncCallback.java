package com.example.eiriksandberg.munchies;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public interface AsyncCallback<T> {
    public void successCallback(T result);
    public void errorCallback();
}

