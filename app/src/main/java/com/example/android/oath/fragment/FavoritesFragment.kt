package com.example.android.oath.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.oath.R
import com.example.android.oath.adapter.FavoritesAdapter
import com.example.android.oath.model.Favorite
import com.example.android.oath.repository.FavoritesRepository
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject() lateinit var favoritesRepository: FavoritesRepository
    private var favorites: MutableList<Favorite> = arrayListOf()

    private lateinit var containerView: View
    override fun getView(): View? {
        return containerView
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containerView = inflater.inflate(R.layout.fragment_favorites, container, false)
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = FavoritesAdapter(favorites, favoritesRepository)
        return containerView
    }

    override fun onResume() {
        super.onResume()
        getFavorites()
    }

    private fun getFavorites() {
        favoritesRepository.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ f ->
                    favorites.clear()
                    favorites.addAll(f)
                    error_text_view.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    recycler_view.adapter.notifyDataSetChanged()
                }, { error ->
                    error.printStackTrace()
                    error_text_view.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                })
    }

    companion object {

        const val TAG = "FAVORITES_FRAGMENT"

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }

    }

}
