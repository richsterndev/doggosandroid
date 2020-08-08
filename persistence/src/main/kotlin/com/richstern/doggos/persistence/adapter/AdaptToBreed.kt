package com.richstern.doggos.persistence.adapter

import com.richstern.doggos.model.Breed
import com.richstern.doggos.persistence.database.BreedEntity
import javax.inject.Inject

class AdaptToBreed @Inject constructor() {
    operator fun invoke(breedEntity: BreedEntity): Breed =
        Breed(
            name = breedEntity.name,
            imageUrl = breedEntity.imageUrl
        )
}