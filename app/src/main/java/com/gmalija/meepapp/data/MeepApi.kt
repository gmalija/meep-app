package com.gmalija.meepapp.data

import com.gmalija.meepapp.domain.Pin
import retrofit2.http.GET
import retrofit2.http.Query

interface MeepApi {
    @GET("routers/lisboa/resources")
    suspend fun getPins(
        @Query(value="lowerLeftLatLon") lowerLeftLatLon: String,
        @Query(value="upperRightLatLon") upperRightLatLon: String): List<Pin>
}