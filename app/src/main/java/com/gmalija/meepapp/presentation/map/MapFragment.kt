package com.gmalija.meepapp.presentation.map

import android.os.Bundle
import android.view.View
import com.gmalija.meepapp.R
import com.gmalija.meepapp.databinding.FragmentMapBinding
import com.gmalija.meepapp.presentation.base.BaseFragment
import com.gmalija.meepapp.util.ClusterRenderer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MapFragment: BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel: MapViewModel by viewModel()
    private val clusterManager: ClusterManager<ClusterMapItem> by inject {
        parametersOf(googleMap)
    }
    private val clusterRenderer: ClusterRenderer by inject {
        parametersOf(googleMap, clusterManager)
    }

    override fun getLayoutResId() = R.layout.fragment_map
    private lateinit var googleMap: GoogleMap

    override fun init(view: View, savedInstanceState: Bundle?) {
        map.run {
            onCreate(savedInstanceState)
            getMapAsync {
                googleMap = it
                clusterManager.run {
                    renderer = clusterRenderer
                }
                it.setOnCameraIdleListener {
                    viewModel.onMapMoved(it.projection.visibleRegion.latLngBounds)
                }
                renderUI()
            }
        }
    }

    private fun renderUI() {
        viewModel.moveData.observe(viewLifecycleOwner, {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(it, 0))
        })
        viewModel.itemsData.observe(viewLifecycleOwner, {
            it.forEach { clusterManager.addItem(it) }
            clusterManager.cluster()
        })
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

}