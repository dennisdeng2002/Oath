package com.example.android.oath.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.oath.R
import com.example.android.oath.repository.NewsRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject() lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setup()
    }

    private fun setup() {
        getArticles(false)
    }

    private fun getArticles(refresh: Boolean) {
        newsRepository.getArticles(refresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { println(it.size) }
    }

}
