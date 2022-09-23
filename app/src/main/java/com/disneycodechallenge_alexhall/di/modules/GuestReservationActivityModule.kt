package com.disneycodechallenge_alexhall.di.modules


import com.disneycodechallenge_alexhall.presentation.activity.GuestReservationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class GuestReservationActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeGuestReservationActivity(): GuestReservationActivity
}
