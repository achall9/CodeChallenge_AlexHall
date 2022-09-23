package com.disneycodechallenge_alexhall.di.modules

import dagger.Module


@Module(
    includes = [
        ViewModelModule::class,
    ]
)
class AppModule