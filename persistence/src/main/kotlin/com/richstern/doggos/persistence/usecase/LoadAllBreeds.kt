package com.richstern.doggos.persistence.usecase

import com.richstern.doggos.model.Breed
import com.richstern.doggos.persistence.adapter.AdaptToBreed
import com.richstern.doggos.persistence.database.BreedDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadAllBreeds @Inject constructor(
    private val breedDao: BreedDao,
    private val adaptToBreed: AdaptToBreed
) {
    operator fun invoke(): Flow<List<Breed>> =
        flow {
            breedDao.getAll().collect { entities ->
                emit(
                    entities.map { breedEntity ->
                        adaptToBreed(breedEntity)
                    }
                )
            }
        }
}