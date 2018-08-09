package com.example.android.oath.dagger

import com.example.android.oath.OathApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val oathApp: OathApp) {

    @Provides
    @Singleton
    fun providesApplication(): OathApp {
        return oathApp
    }

}
