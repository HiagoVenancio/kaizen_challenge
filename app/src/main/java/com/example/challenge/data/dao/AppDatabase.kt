package com.example.challenge.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.challenge.domain.model.SportModel

@Database(entities = [SportModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportsDao(): SportDao
}