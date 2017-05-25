package com.example.eiriksandberg.munchies.Adapter;

/**
 * Created by eiriksandberg on 19.05.2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eiriksandberg.munchies.Domain.Place;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eiriksandberg.munchies.MainActivity;
import com.example.eiriksandberg.munchies.R;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class SwipeCardAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<Place> places;

    public SwipeCardAdapter (Context context, ArrayList<Place> places) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.places = places;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("PLACES.SIZE(): " + places.size());
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null){
            vi = inflater.inflate(R.layout.item, parent, false);
            TextView tv = (TextView) vi.findViewById(R.id.nameText);
            Place p = (Place) getItem(position);
            tv.setText(p.getName());
        }
        return vi;
    }
}
