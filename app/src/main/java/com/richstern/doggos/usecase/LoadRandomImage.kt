package com.richstern.doggos.usecase

import com.richstern.doggos.core.network.DoggosService
import javax.inject.Inject

class LoadRandomImage @Inject constructor(
    private val doggosService: DoggosService
) {
    operator fun invoke() {
        // TODO: Fetch and return random image
    }
}