package com.example.auth.di

import org.koin.dsl.module

val authFeatureModule = module {
    includes(viewModelModule)
}