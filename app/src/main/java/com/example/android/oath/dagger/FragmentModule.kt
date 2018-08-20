package com.example.android.oath.dagger

import com.example.android.oath.fragment.FavoritesFragment
import com.example.android.oath.fragment.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFavoritesFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

}
