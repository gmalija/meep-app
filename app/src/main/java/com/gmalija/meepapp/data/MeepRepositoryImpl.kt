package com.gmalija.meepapp.data

import com.gmalija.meepapp.domain.Pin
import com.gmalija.meepapp.domain.Result
import timber.log.Timber

class MeepRepositoryImpl(private val meepApi: MeepApi): MeepRepository {

    override suspend fun getAllPins(square: Square): Result<List<Pin>> {
        return try {
            val result = meepApi.getPins(
                "${square.southwestLatitude},${square.southwestLongitude}",
                "${square.northeastLatitude},${square.northeastLongitude}"
            )
            Result.success(result)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.error(ex)
        }
    }

}