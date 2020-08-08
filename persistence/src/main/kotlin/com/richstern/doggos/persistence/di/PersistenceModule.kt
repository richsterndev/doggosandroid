package com.richstern.doggos.persistence.di

import android.content.Context
import androidx.room.Room
import com.richstern.doggos.persistence.database.AppDatabase
import com.richstern.doggos.persistence.database.BreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class PersistenceModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "doggos_db"
        ).build()

    @Provides
    fun provideBreedDao(
        appDatabase: AppDatabase
    ): BreedDao = appDatabase.breedDao()
}