package com.example.scenariosgames.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
import com.example.scenariosgames.creationGame.GameActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailsGame : ComponentActivity() {
        private lateinit var descriptionText:EditText
        private lateinit var RequirementsText:EditText
        private lateinit var ContentView:TextView
        private lateinit var exploration:Button
        private lateinit var LevelUp:Button
        private lateinit var Hoursofadminsion:Button
        private lateinit var MapView:MapView
        private lateinit var require1:TextView
        private lateinit var require2:TextView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detailsjeux)

            descriptionText = findViewById(R.id.Description)
            RequirementsText = findViewById(R.id.Requirements)
            ContentView = findViewById(R.id.DescriptionText)
            exploration = findViewById(R.id.button2)
            LevelUp = findViewById(R.id.UpLevel)
            Hoursofadminsion = findViewById(R.id.HoofAdmission)
            require1 = findViewById(R.id.require1)
            require2 = findViewById(R.id.require2)

            MapView = findViewById(R.id.mapView)
            MapView.onCreate(savedInstanceState)

            MapView.getMapAsync { googleMap ->
                val initialPosition = LatLng(37.7749, -122.4194) // San Francisco, CA

                // Move the camera to the initial position with a certain zoom level
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, 12f))

                // Add a marker to the map
                googleMap.addMarker(
                    MarkerOptions()
                        .position(initialPosition)
                        .title("San Francisco")
                        .snippet("A beautiful city by the bay")
                )

                // Enable various user interactions
                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isCompassEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true


            }


            exploration.setOnClickListener {
                 val intent = Intent(this,ExploreGame::class.java)
                 startActivity(intent)
             }

            LevelUp.setOnClickListener {
                val intent = Intent(this,GameActivity::class.java)
                startActivity(intent)

            }
            Hoursofadminsion.setOnClickListener {
                val intent = Intent(this,ExploreGame::class.java)
                startActivity(intent)
            }

        }



}
