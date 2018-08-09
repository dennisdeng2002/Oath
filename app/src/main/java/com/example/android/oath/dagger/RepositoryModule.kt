package com.example.android.oath.dagger

import com.example.android.oath.api.NewsApi
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

}
