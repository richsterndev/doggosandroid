package com.richstern.doggos.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BreedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}