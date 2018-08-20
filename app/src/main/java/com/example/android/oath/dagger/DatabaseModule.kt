package com.example.android.oath.dagger

import android.arch.persistence.room.Room
import com.example.android.oath.OathApp
import com.example.android.oath.database.FavoritesDao
import com.example.android.oath.database.OathDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val databaseName: String) {

    @Provides
    @Singleton
    fun providesOathDatabase(oathApp: OathApp): OathDatabase {
        return Room.databaseBuilder<OathDatabase>(
                oathApp.applicationContext,
                OathDatabase::class.java,
                databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun providesFavoritesDao(oathDatabase: OathDatabase): FavoritesDao {
        return oathDatabase.getFavoritesDao()
    }

}
