package com.example.drive

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.drive.model.GeoLocation
import com.example.drive.model.RoadProblem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class RoadProblemMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        var roadProblem: RoadProblem? = null
        var geoLocationClick: GeoLocation = GeoLocation(53.226184, 50.195240)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_problem_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener(GoogleMapOnClickListener(this))
        if (roadProblem != null) {
            val geoLocation =
                LatLng(roadProblem!!.geoLocation.height, roadProblem!!.geoLocation.width)
            // Add a marker in Samara and move the camera
            mMap.addMarker(MarkerOptions().position(geoLocation).title(roadProblem!!.title))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation, 17f))
        }
        else {
            val geoLocation =
                LatLng(geoLocationClick.height, geoLocationClick.width)
            // Add a marker in Samara and move the camera
            mMap.addMarker(MarkerOptions().position(geoLocation).title("Самара"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation, 8f))
        }
    }
}

class GoogleMapOnClickListener(private val context: Context) : GoogleMap.OnMapClickListener {
    override fun onMapClick(point: LatLng) {
        RoadProblemMapActivity.geoLocationClick = GeoLocation(
            height = point.latitude,
            width = point.longitude
        )

        val intent = Intent(context, AddRoadProblemActivity::class.java)
        showSnackbar("Геолокация проблемной зоны выбрана")
        startActivity(context, intent, null)
    }

    fun showSnackbar(text: String) {
        Snackbar.make(RoadProblemActivity.rv, text, Snackbar.LENGTH_LONG).show()
    }
}
