package com.example.android.oath.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.android.oath.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class OathDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao

}
