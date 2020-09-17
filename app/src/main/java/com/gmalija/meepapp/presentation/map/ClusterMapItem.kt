package com.gmalija.meepapp.presentation.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ClusterMapItem(val id: String,
                          val name: String,
                          val companyZoneId: Int,
                          val color: Float,
                          val latitude: Double,
                          val longitude: Double,
) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSnippet(): String {
        return companyZoneId.toString()
    }
}