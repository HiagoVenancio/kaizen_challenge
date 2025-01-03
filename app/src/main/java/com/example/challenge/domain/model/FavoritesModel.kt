package com.example.challenge.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteModel(
    @PrimaryKey val eventId: String
)