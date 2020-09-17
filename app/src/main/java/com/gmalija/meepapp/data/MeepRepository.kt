package com.gmalija.meepapp.data

import com.gmalija.meepapp.domain.Pin
import com.gmalija.meepapp.domain.Result

interface MeepRepository {
    suspend fun getAllPins(square: Square): Result<List<Pin>>
}