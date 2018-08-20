package com.example.android.oath.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.oath.R
import com.example.android.oath.model.Favorite
import com.example.android.oath.repository.FavoritesRepository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_article.*

class FavoritesAdapter(
        private val favorites: List<Favorite>,
        private val favoritesRepository: FavoritesRepository
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_favorite, parent, false)
        return FavoriteViewHolder(v)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindArticle(favorites[position], favoritesRepository)
    }

    class FavoriteViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        lateinit var favorite: Favorite
        var isFavorite = true
        lateinit var favoritesRepository: FavoritesRepository

        fun bindArticle(favorite: Favorite, favoritesRepository: FavoritesRepository) {
            this.favorite = favorite
            this.favoritesRepository = favoritesRepository

            title_text_view.text = favorite.title
            summary_text_view.text = favorite.summary
            isFavorite = favoritesRepository.isFavorite(favorite.id)
            if (isFavorite) {
                favorite_image_view.setBackgroundResource(R.drawable.heart)
            } else {
                favorite_image_view.setBackgroundResource(R.drawable.heart_outline)
            }

            favorite_image_view.setOnClickListener {
                toggleFavorite()
            }
        }

        private fun toggleFavorite() {
            if (!isFavorite) {
                favorite_image_view.setBackgroundResource(R.drawable.heart)
                favoritesRepository.addToFavorites(favorite)
            } else {
                favorite_image_view.setBackgroundResource(R.drawable.heart_outline)
                favoritesRepository.removeFromFavorites(favorite.id)
            }

            isFavorite = !isFavorite
        }

    }

}
