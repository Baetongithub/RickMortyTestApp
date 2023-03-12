package com.example.rickmortytestapp.di

import android.content.Context
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.presentation.utils.network_helper.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import com.example.rickmortytestapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideNetwork(okHttpClient = get()) }
    factory { provideOkHttpClient(context = get()) }
    single { provideAPI(retrofit = get()) }
}

fun provideNetwork(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(NetworkConnectionInterceptor(context))
        .build()
}

fun provideAPI(retrofit: Retrofit): RickMortyAPI = retrofit.create(RickMortyAPI::class.java)
