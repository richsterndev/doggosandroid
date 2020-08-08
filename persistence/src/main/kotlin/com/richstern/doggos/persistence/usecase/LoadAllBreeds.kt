package com.richstern.doggos.persistence.usecase

import com.richstern.doggos.model.Breed
import com.richstern.doggos.persistence.database.BreedDao
import com.richstern.doggos.persistence.adapter.AdaptToBreed
import javax.inject.Inject

class LoadAllBreeds @Inject constructor(
    private val breedDao: BreedDao,
    private val adaptToBreed: AdaptToBreed
) {
    operator fun invoke(): List<Breed> =
        breedDao.getAll().map {
            adaptToBreed(it)
        }
}