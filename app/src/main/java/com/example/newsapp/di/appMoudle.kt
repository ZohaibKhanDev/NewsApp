package com.example.newsapp.di

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.newsapp.newsapi.MainViewModels
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.roomdatabase.DataBase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DataBase::class.java,
            "demo.db"

        ).allowMainThreadQueries()
            .build()
    }
    single { Repository(get()) }
    viewModelOf(::MainViewModels)
}