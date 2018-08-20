package com.example.android.oath.dagger

import com.example.android.oath.api.NewsApi
import com.example.android.oath.database.FavoritesDao
import com.example.android.oath.repository.FavoritesRepository
import com.example.android.oath.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepository(newsApi)
    }

    @Provides
    @Singleton
    fun providesFavoritesRepository(favoritesDao: FavoritesDao): FavoritesRepository {
        return FavoritesRepository(favoritesDao)
    }

}
