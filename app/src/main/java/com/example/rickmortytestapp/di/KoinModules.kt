package com.example.rickmortytestapp.di

import androidx.paging.ExperimentalPagingApi

@ExperimentalPagingApi
val koinModules = listOf(appModule, domainModule, dataModule, networkModule)