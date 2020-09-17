package com.gmalija.meepapp.domain

import com.google.gson.annotations.SerializedName

data class Pin(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("x") val x: Double,
    @SerializedName("y") val y: Double,
    @SerializedName("companyZoneId") val companyZoneId: Int,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("licencePlate") val licencePlate: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("helmets") val helmets: Int?,
    @SerializedName("batteryLevel") val batteryLevel: Int?,
    @SerializedName("seats") val seats: Int?,
    @SerializedName("resourceType") val resourceType: String?,
    @SerializedName("engineType") val engineType: String?,
    @SerializedName("pricePerMinuteParking") val pricePerMinuteParking: Double?,
    @SerializedName("pricePerMinuteDriving") val pricePerMinuteDriving: Double?
)