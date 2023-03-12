package com.example.rickmortytestapp.di

import android.content.Context
import androidx.room.Room
import com.example.rickmortytestapp.data.local.RickMortyDatabase
import com.example.rickmortytestapp.data.repository.CharacterRepositoryImpl
import com.example.rickmortytestapp.data.repository.EpisodeRepositoryImpl
import com.example.rickmortytestapp.data.repository.LocationRepositoryImpl
import com.example.rickmortytestapp.domain.repository.CharacterRepository
import com.example.rickmortytestapp.domain.repository.EpisodeRepository
import com.example.rickmortytestapp.domain.repository.LocationRepository
import org.koin.dsl.module

val dataModule = module {
    //repository
    single<CharacterRepository> {
        CharacterRepositoryImpl(
            rickMortyAPI = get(),
            rickMortyDatabase = get()
        )
    }
    single<LocationRepository> {
        LocationRepositoryImpl(
            rickMortyAPI = get(),
            rickMortyDatabase = get()
        )
    }
    single<EpisodeRepository> {
        EpisodeRepositoryImpl(
            rickMortyAPI = get(),
            rickMortyDatabase = get()
        )
    }

    //database
    single { provideDatabase(context = get()) }
}

fun provideDatabase(context: Context): RickMortyDatabase {
    return Room.databaseBuilder(
        context = context,
        klass = RickMortyDatabase::class.java,
        name = "rick_morty_db_name"
    ).fallbackToDestructiveMigration()
        .build()
}
