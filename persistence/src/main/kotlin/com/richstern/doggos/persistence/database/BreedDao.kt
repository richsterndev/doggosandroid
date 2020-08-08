package com.richstern.doggos.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {
    @Query("SELECT * from breeds")
    suspend fun getAll(): List<BreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breedEntity: BreedEntity)
}