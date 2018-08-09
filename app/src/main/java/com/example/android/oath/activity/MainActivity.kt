package com.example.android.oath.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.oath.R
import com.example.android.oath.adapter.NewsAdapter
import com.example.android.oath.model.Article
import com.example.android.oath.repository.NewsRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject() lateinit var newsRepository: NewsRepository
    private var articles: MutableList<Article> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setup()
    }

    private fun setup() {
        swipe_refresh_layout.setOnRefreshListener { getArticles(true) }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = NewsAdapter(articles)
        getArticles(false)
    }

    private fun getArticles(refresh: Boolean) {
        newsRepository.getArticles(refresh)
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

}
