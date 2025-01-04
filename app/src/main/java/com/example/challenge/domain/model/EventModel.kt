package com.example.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventModel(
    val id: String,
    val sportId: String,
    val team1: String,
    val team2: String,
    var startTime: Long,
    var isFavorite: Boolean = false,
    var remainingTime: Long = 0L,
    val sportName: String
) : Parcelable