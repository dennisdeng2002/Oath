package com.example.android.oath.repository

import com.example.android.oath.api.NewsApi
import com.example.android.oath.model.Article
import io.reactivex.Maybe

class NewsRepository(private val newsApi: NewsApi) {

    var articles: Array<Article>? = null

    fun getArticles(refresh: Boolean): Maybe<Array<Article>> {
        if (articles != null && !refresh) return Maybe.just(articles)
        return newsApi.getArticles()
                .map { a ->
                    articles = a
                    return@map articles
                }
    }

}
