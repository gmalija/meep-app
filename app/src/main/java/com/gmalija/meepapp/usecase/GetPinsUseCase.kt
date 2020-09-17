package com.gmalija.meepapp.usecase

import com.gmalija.meepapp.data.MeepRepository
import com.gmalija.meepapp.data.Square

class GetPinsUseCase(private val meepRepository: MeepRepository) {
    suspend fun getPins(square: Square) = meepRepository.getAllPins(square)
}