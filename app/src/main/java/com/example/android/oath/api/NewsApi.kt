package com.example.android.oath.api

import com.example.android.oath.model.Article
import io.reactivex.Maybe
import retrofit2.http.GET

interface NewsApi {
    @GET("newsfeed.json")
    fun getArticles(): Maybe<Array<Article>>
}
