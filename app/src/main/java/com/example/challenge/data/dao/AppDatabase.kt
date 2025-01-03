package com.example.challenge.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.challenge.domain.model.FavoriteModel
import com.example.challenge.domain.model.SportModel

@Database(
    entities = [
        SportModel::class,
        FavoriteModel::class
    ], version = 3
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportsDao(): SportDao
    abstract fun favoritesDao(): FavoriteDao
}