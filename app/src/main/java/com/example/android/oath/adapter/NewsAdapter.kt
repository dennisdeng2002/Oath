package com.example.android.oath.adapter

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.oath.R
import com.example.android.oath.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_article.*

class NewsAdapter(
        private val context: Context,
        private val articles: List<Article>
) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
        return ArticleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindArticle(articles[position], context)
    }

    class ArticleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindArticle(article: Article, context: Context) {
            title_text_view.text = article.content.title
            if (article.content.images.isNotEmpty() && article.content.images[0].resolutions.isNotEmpty()) {
                Picasso.get()
                        .load(article.content.images[0].resolutions[0].url)
                        .placeholder(android.R.color.darker_gray)
                        .error(android.R.color.black)
                        .into(image_view)
            }
            summary_text_view.text = article.content.summary
            topics_text_view.text = article.topics
            containerView.setOnClickListener {
                CustomTabsIntent.Builder()
                        .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .build()
                        .launchUrl(context, Uri.parse(article.content.url))
            }
        }

    }

}
