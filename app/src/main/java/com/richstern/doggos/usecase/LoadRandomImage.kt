package com.richstern.doggos.usecase

import com.richstern.doggos.core.network.DoggosService
import com.richstern.doggos.model.Breed
import com.richstern.doggos.model.adapter.AdaptRandomImageResultToBreed
import javax.inject.Inject

class LoadRandomImage @Inject constructor(
    private val doggosService: DoggosService,
    private val adaptRandomImageResultToBreed: AdaptRandomImageResultToBreed
) {
    suspend operator fun invoke(): Breed? {
        return doggosService.getRandomImage().message?.let {
            adaptRandomImageResultToBreed(it)
        }
    }
}