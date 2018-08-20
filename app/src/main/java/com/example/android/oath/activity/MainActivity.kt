package com.example.android.oath.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.example.android.oath.R
import com.example.android.oath.fragment.FavoritesFragment
import com.example.android.oath.fragment.NewsFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val newsFragment = NewsFragment.newInstance()
    val favoritesFragment = FavoritesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setup()
    }

    private fun setup() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, newsFragment, NewsFragment.TAG)
                .commit()

        navigation_view.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawers()

            var fragment: Fragment? = null
            var tag: String? = null

            when (item.itemId) {
                R.id.news -> {
                    fragment = newsFragment
                    tag = NewsFragment.TAG
                }

                R.id.favorites -> {
                    fragment = favoritesFragment
                    tag = FavoritesFragment.TAG
                }
            }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit()

            true
        }
    }

}
