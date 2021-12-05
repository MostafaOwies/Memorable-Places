package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> myPlaces = new ArrayList<>();
    static  ArrayList<LatLng> locations = new ArrayList<LatLng>();
    static   ArrayAdapter arrayAdapter;

    public void addNew (View view){
        Intent intent = new Intent(getApplicationContext(),MyMap.class);
        intent.putExtra("addnew","me");
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.memorableplaces",Context.MODE_PRIVATE);

        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> longitudes = new ArrayList<>();
        myPlaces.clear();
        latitudes.clear();
        longitudes.clear();
        locations.clear();
        try {
            myPlaces= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("Places",ObjectSerializer.serialize(new ArrayList<String>())));
            latitudes= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("lats",ObjectSerializer.serialize(new ArrayList<String>())));
            longitudes= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longs",ObjectSerializer.serialize(new ArrayList<String>())));

        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (myPlaces.size()>0 && latitudes.size()>0 && longitudes.size()>0){
            if (myPlaces.size()==latitudes.size()&& myPlaces.size()==longitudes.size()) {
                for (int i=0 ; i<latitudes.size();i++){
                    locations.add(new LatLng(Double.parseDouble(latitudes.get(i)),Double.parseDouble(longitudes.get(i))));
                }
            }
        }



        ListView listView = findViewById(R.id.mylist);

         arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,myPlaces);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MyMap.class);
                intent.putExtra("name",position);
                startActivity(intent);
            }
        });
    }
}