package com.example.wil_summativeproject_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// --- CORRECTED: Added all necessary Google Maps imports ---
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// Implement OnMapReadyCallback to know when the map is ready to be used
class _ContactPage : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_contact_us)

        // Find the map fragment from the layout
        // --- CORRECTED: The ID is R.id.map_fragment, not R.id.map ---
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment

        // Start the process of getting the map ready.
        // When it's ready, the onMapReady function will be called.
        // --- CORRECTED: Removed the duplicate call ---
        mapFragment.getMapAsync(this)
    }

    /**
     * This function is automatically called when the map is loaded and ready.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 1. Get the coordinates from your URL
        val bonifayLocation = LatLng(-26.1318003, 27.965015)

        // 2. Add a marker at that location
        mMap.addMarker(MarkerOptions().position(bonifayLocation).title("Marker at Bonifay Court"))

        // 3. Move the camera to the location and set the zoom level (17f is a good zoom level)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bonifayLocation, 17f))

        // Optional: You can also enable UI controls like zoom buttons
        mMap.uiSettings.isZoomControlsEnabled = true
    }
}
