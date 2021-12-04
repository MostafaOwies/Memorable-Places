package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

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