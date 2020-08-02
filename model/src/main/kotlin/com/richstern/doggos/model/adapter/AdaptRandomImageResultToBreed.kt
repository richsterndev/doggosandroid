package com.richstern.doggos.model.adapter

import com.richstern.doggos.model.Breed
import javax.inject.Inject

private const val PREFIX = "https://images.dog.ceo/breeds/"

class AdaptRandomImageResultToBreed @Inject constructor() {
    operator fun invoke(imageUrl: String): Breed {
        val lastIndexOfSlash = imageUrl.indexOf('/', PREFIX.length)
        val rawName = imageUrl.subSequence(PREFIX.length, lastIndexOfSlash).toString()
        return Breed(rawName, imageUrl)
    }
}