package com.disneycodechallenge_alexhall.di

import android.app.Application
import com.disneycodechallenge_alexhall.App
import com.disneycodechallenge_alexhall.di.modules.AppModule
import com.disneycodechallenge_alexhall.di.modules.GuestReservationActivityModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        GuestReservationActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}
