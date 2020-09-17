package com.gmalija.meepapp.util

fun getColor(companyZoneId: Int): Float {
    // Because top HUE color is 330f
    return (companyZoneId % 165.0f)
}