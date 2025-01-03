package com.example.challenge.di

import androidx.room.Room
import com.example.challenge.data.api.DataApi
import com.example.challenge.data.dao.AppDatabase
import com.example.challenge.data.repository.SportRepository
import com.example.challenge.ui.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://618d3aa7fe09aa001744060a.mockapi.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "sports_database"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().sportsDao() }
    single { get<AppDatabase>().favoritesDao() }

    single { get<Retrofit>().create(DataApi::class.java) }
    single<SportRepository> { SportRepository(get(), get()) }
    single { MainViewModel(get()) }
}