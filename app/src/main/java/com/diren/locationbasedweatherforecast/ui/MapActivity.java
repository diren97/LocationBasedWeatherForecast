package com.diren.locationbasedweatherforecast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.diren.locationbasedweatherforecast.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity {

    AppCompatButton search_city_btn;
    AppCompatButton logout_btn;
    TextView txtNightMode;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_map);

        SwitchCompat switchCompat = findViewById(R.id.switchCompat);
        search_city_btn=findViewById(R.id.search_city_btn);
        logout_btn= findViewById(R.id.logout_btn);
        txtNightMode = findViewById(R.id.txtNightMode);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                finish();
                startActivity(new Intent(MapActivity.this, MapActivity.this.getClass()));

            }
        });
    }



}