package com.example.android.oath.dagger

import com.example.android.oath.OathApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    ActivityModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DatabaseModule::class,
    FragmentModule::class,
    NetModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent {
    fun inject(oathApp: OathApp)
}
