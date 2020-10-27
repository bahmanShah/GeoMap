package org.gladcherry.map.view.map

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.gladcherry.map.R
import org.gladcherry.map.base.DatabindingActivity
import org.gladcherry.map.databinding.ActivityMapBinding
import org.gladcherry.map.util.M_TAG
import org.gladcherry.map.util.extention.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapActivity : DatabindingActivity() {
    var map: GoogleMap? = null
    val binding: ActivityMapBinding by binding(R.layout.activity_map)
    private val viewModel: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMVVM()
        mapInit()
        observe()
        textWatcher()
    }

    private fun observe() {
        viewModel.currentGeo.observe(this, {
            Toast.makeText(this@MapActivity, it.address, Toast.LENGTH_LONG).show()
        })
        viewModel.clickedViewId.observe(this, {
            if (it == R.id.backSearch)
                invisibleSearchLayout()
        })
        viewModel.currentSearchId.observe(this, {
            it?.let {
                invisibleSearchLayout()
                moveCameraToSearchPosition(it)
            }
        })
    }

    private fun moveCameraToSearchPosition(it: Int) {
        map?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    viewModel.searchList.value?.searchItems?.get(it)?.geom!!.coordinates[1],
                    viewModel.searchList.value?.searchItems?.get(it)?.geom!!.coordinates[0]
                ), 16f
            )
        )
    }

    private fun invisibleSearchLayout() {
        viewModel.changeSearchVisibility(false)
        binding.rootLayout.hideKeyboard()
    }

    private fun mapInit() {
        (supportFragmentManager
            .findFragmentById(R.id.mapContainer) as SupportMapFragment).getMapAsync {
            it ?: return@getMapAsync
            mapInit(it)
        }
    }

    private fun mapInit(it: GoogleMap) {
        map = it
        it.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(35.718421, 51.389483), 14f))
            setMinZoomPreference(12f)
            uiSettings?.isCompassEnabled = false
            uiSettings?.isMapToolbarEnabled = false
            setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this@MapActivity, R.raw.map_aube_style)
            )
            it.setOnMapClickListener { point ->
                addMarker(it, point)
                viewModel.reverseGeo(point.latitude, point.longitude)
            }
        }
    }

    private fun addMarker(googleMap: GoogleMap, position: LatLng) {
        googleMap.clear()
        googleMap.addMarker(
            MarkerOptions()
                .position(position)
                .icon(
                    BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_marker)
                )
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16f))
    }

    private fun textWatcher() {
        var searchFor = ""
        binding.searchEditText.apply {
            doOnTextChanged { text, start, before, count ->
                val searchText = text.toString().trim()
                if (searchText == searchFor)
                    return@doOnTextChanged
                searchFor = searchText

                lifecycleScope.launch(Dispatchers.IO) {
                    delay(500)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch
                    doSearch(text.toString())
                    Log.d(M_TAG, text.toString())
                }
            }
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    viewModel.changeSearchVisibility(true)
            }
        }


    }

    private fun doSearch(textSearch: String) {
        viewModel.doSearch(textSearch)
    }

    private fun initMVVM() {
        binding.apply {
            lifecycleOwner = this@MapActivity
            vm = viewModel
        }
        lifecycle.addObserver(viewModel)
    }

}