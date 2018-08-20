package com.example.android.oath.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.oath.R
import com.example.android.oath.adapter.NewsAdapter
import com.example.android.oath.model.Article
import com.example.android.oath.repository.FavoritesRepository
import com.example.android.oath.repository.NewsRepository
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject() lateinit var newsRepository: NewsRepository
    @Inject() lateinit var favoritesRepository: FavoritesRepository
    private var articles: MutableList<Article> = arrayListOf()

    private lateinit var containerView: View
    override fun getView(): View? {
        return containerView
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containerView = inflater.inflate(R.layout.fragment_news, container, false)
        setup()
        return containerView
    }

    private fun setup() {
        swipe_refresh_layout.setOnRefreshListener { getArticles(true) }
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = NewsAdapter(activity!!, articles, favoritesRepository)
        getArticles(false)
    }

    private fun getArticles(refresh: Boolean) {
        favoritesRepository.getFavorites()
                .flatMap { _ -> newsRepository.getArticles(refresh) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ a ->
                    articles.clear()
                    articles.addAll(a)
                    error_text_view.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    swipe_refresh_layout.isRefreshing = false
                    recycler_view.adapter.notifyDataSetChanged()
                }, { error ->
                    error.printStackTrace()
                    error_text_view.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                    swipe_refresh_layout.isRefreshing = false
                })
    }

    companion object {

        const val TAG = "NEWS_FRAGMENT"

        fun newInstance(): NewsFragment {
            return NewsFragment()
        }

    }

}
