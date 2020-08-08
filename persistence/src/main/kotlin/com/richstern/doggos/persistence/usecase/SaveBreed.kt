package com.richstern.doggos.persistence.usecase

import com.richstern.doggos.model.Breed
import com.richstern.doggos.persistence.database.BreedDao
import com.richstern.doggos.persistence.adapter.AdaptToBreedEntity
import javax.inject.Inject

class SaveBreed @Inject constructor(
    private val breedDao: BreedDao,
    private val adaptToBreedEntity: AdaptToBreedEntity
) {
    operator fun invoke(breed: Breed) {
        breedDao.insert(adaptToBreedEntity(breed))
    }
}