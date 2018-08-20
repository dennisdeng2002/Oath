package com.example.android.oath.repository

import com.example.android.oath.database.FavoritesDao
import com.example.android.oath.model.Article
import com.example.android.oath.model.Favorite
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FavoritesRepository(
        private var favoritesDao: FavoritesDao
) {

    var favorites = HashMap<String, Favorite>()
    private var initialized = false

    fun isFavorite(id: String): Boolean {
        return favorites.containsKey(id)
    }

    fun addArticleToFavorites(article: Article) {
        val favorite = Favorite(article.id, article.content.title, article.content.summary)
        favorites[article.id] = favorite
        commitToDatabase()
    }

    fun addToFavorites(favorite: Favorite) {
        favorites[favorite.id] = favorite
        commitToDatabase()
    }

    fun removeFromFavorites(id: String) {
        favorites.remove(id)
        commitToDatabase()
    }

    private fun commitToDatabase() {
        Observable.create<Unit> { emitter ->
            favoritesDao.deleteAll()
            favoritesDao.insertAll(favorites.values.toList())
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getFavorites(): Maybe<List<Favorite>> {
        if (initialized) return Maybe.just(favorites.values.toList())
        return favoritesDao.getAllFavorites()
                .map { f ->
                    f.forEach { favorite ->
                        favorites[favorite.id] = favorite
                    }

                    initialized = true
                    return@map f
                }
    }

}
