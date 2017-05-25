package com.example.eiriksandberg.munchies.Domain;

import android.graphics.Bitmap;

/**
 * Created by eiriksandberg on 22.05.2017.
 */

public class AttributedPhoto {

    public final CharSequence attribution;

    public final Bitmap bitmap;

    public AttributedPhoto(CharSequence attribution, Bitmap bitmap) {
        this.attribution = attribution;
        this.bitmap = bitmap;
    }
}
