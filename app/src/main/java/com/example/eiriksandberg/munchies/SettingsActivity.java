package com.example.eiriksandberg.munchies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.eiriksandberg.munchies.Adapter.SettingsAdapter;

import java.util.List;
import java.util.Set;

/**
 * Created by eiriksandberg on 22.05.2017.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ListView lv = (ListView) findViewById(R.id.settingsList);
        SettingsAdapter adapter = new SettingsAdapter(this, 20);
        lv.setAdapter(adapter);
    }

}
