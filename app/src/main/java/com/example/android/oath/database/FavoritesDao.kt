package com.example.android.oath.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.android.oath.model.Favorite
import io.reactivex.Maybe

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Favorite")
    fun getAllFavorites(): Maybe<List<Favorite>>

    @Query("DELETE FROM Favorite")
    fun deleteAll()

    @Insert
    fun insertAll(favorites: List<Favorite>)

}
