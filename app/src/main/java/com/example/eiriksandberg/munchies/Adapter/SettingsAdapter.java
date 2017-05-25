package com.example.eiriksandberg.munchies.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eiriksandberg.munchies.MainActivity;
import com.example.eiriksandberg.munchies.R;

/**
 * Created by eiriksandberg on 13.05.2017.
 */

public class SettingsAdapter extends BaseAdapter{
    static final int MAX_TYPE_COUNT = 2;
    static final int SEEKBAR_ITEM = 0;
    static final int SELECT_TYPE_OF_CUISINE = 1;


        Context context;
        private static LayoutInflater inflater = null;
        private int radius;

        public SettingsAdapter(Context context, int radius) {
            // TODO Auto-generated constructor stub
            this.context = context;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.radius = radius;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 2;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position){
                case 0: {
                    return SEEKBAR_ITEM;
                }
                case 1: {
                    return SELECT_TYPE_OF_CUISINE;
                }
                default: break;
            }
            return -1;
        }

    @Override
    public int getViewTypeCount() {
        return MAX_TYPE_COUNT;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi = convertView;
            int type = getItemViewType(position);
        System.out.println("type: " + type + "position: "+ position);
            if (vi == null){
                switch (type){
                    case SEEKBAR_ITEM: vi = inflater.inflate(R.layout.settings_row, null);
                        SeekBar seekBar = (SeekBar) vi.findViewById(R.id.seekBar);
                        final TextView tv = (TextView) vi.findViewById(R.id.radiusText);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress,
                                                          boolean fromUser) {
                                // TODO Auto-generated method stub´¨
                                radius = progress;
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                // TODO Auto-generated method stub
                            }
                        });
                        break;
                    case SELECT_TYPE_OF_CUISINE: vi = inflater.inflate(R.layout.settings_cuisine_row, null);
                        break;
                    default: break;
                }
            }
            return vi;
        }
    }
