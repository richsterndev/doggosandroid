package com.richstern.doggos.persistence.adapter

import com.richstern.doggos.model.Breed
import com.richstern.doggos.persistence.database.BreedEntity
import javax.inject.Inject

class AdaptToBreedEntity @Inject constructor() {
    operator fun invoke(breed: Breed): BreedEntity =
        BreedEntity(
            name = breed.name,
            imageUrl = breed.imageUrl
        )
}