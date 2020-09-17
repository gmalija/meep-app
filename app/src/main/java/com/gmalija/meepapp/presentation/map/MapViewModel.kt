package com.gmalija.meepapp.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmalija.meepapp.data.Square
import com.gmalija.meepapp.domain.Status
import com.gmalija.meepapp.presentation.base.BaseViewModel
import com.gmalija.meepapp.usecase.GetPinsUseCase
import com.gmalija.meepapp.util.SingleLiveEvent
import com.gmalija.meepapp.util.getColor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.launch
import timber.log.Timber

class MapViewModel(private val getPinsUseCase: GetPinsUseCase)
    : BaseViewModel() {

    private val itemsEvent: SingleLiveEvent<Iterable<ClusterMapItem>> = SingleLiveEvent()
    val itemsData: LiveData<Iterable<ClusterMapItem>>
        get() = itemsEvent

    private val moveEvent: SingleLiveEvent<LatLngBounds> = SingleLiveEvent()
    val moveData: LiveData<LatLngBounds>
        get() = moveEvent

    init {
        // Default square by example url
        // https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115
        moveEvent.postValue(LatLngBounds.Builder()
            .include(LatLng(38.711046, -9.160096))
            .include(LatLng(38.739429, -9.137115))
            .build())
    }

    fun onMapMoved(mapSquare: LatLngBounds) {

        val square = Square(
            mapSquare.northeast.latitude,
            mapSquare.southwest.latitude,
            mapSquare.northeast.longitude,
            mapSquare.southwest.longitude
        )

        viewModelScope.launch {
            val result = getPinsUseCase.getPins(square)
            if(result.status == Status.SUCCESS) {
                val list = result.data
                itemsEvent.postValue(list?.map {
                    ClusterMapItem(it.id,
                        it.name,
                        it.companyZoneId,
                        getColor(it.companyZoneId),
                        it.latitude,
                        it.longitude)
                })
            } else {
                Timber.e(result.message)
            }
        }

    }

}