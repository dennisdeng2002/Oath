package com.example.android.oath.misc

import com.example.android.oath.model.Article
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class NewsDeserializer : JsonDeserializer<Array<Article>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<Article> {
        val items = json?.asJsonObject?.get("items")
        val result = items?.asJsonObject?.get("result")
        val articles = result?.asJsonArray
        // probably not a good idea to create a new Gson object every time we want to deserialize
        val gson = GsonBuilder().create()
        return gson.fromJson(articles, Array<Article>::class.java)
    }

}
