package com.gmalija.meepapp.util

import android.content.Context
import com.gmalija.meepapp.presentation.map.ClusterMapItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class ClusterRenderer(context: Context, map: GoogleMap, clusterManager: ClusterManager<ClusterMapItem>)
    : DefaultClusterRenderer<ClusterMapItem>(context, map, clusterManager) {
    override fun onBeforeClusterItemRendered(clusterMapItem: ClusterMapItem, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(clusterMapItem.color))
    }
}