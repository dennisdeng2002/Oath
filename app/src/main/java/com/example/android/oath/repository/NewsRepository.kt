package com.example.android.oath.repository

import com.example.android.oath.api.NewsApi
import com.example.android.oath.model.Article
import io.reactivex.Maybe

class NewsRepository(private val newsApi: NewsApi) {

    var articles: List<Article>? = null

    fun getArticles(refresh: Boolean): Maybe<List<Article>> {
        if (articles != null && !refresh) return Maybe.just(articles)
        return newsApi.getArticles()
                .map { a ->
                    // a is returned as an array
                    articles = listOf(*a)
                    return@map articles
                }
    }

}
