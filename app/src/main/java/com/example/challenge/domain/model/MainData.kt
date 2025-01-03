package com.example.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val id: String,
    val name: String,
    val events: List<SportEvent> = mutableListOf(),
    var isExpanded: Boolean = false
) : Parcelable

@Parcelize
data class SportEvent(
    val id: String,
    val team1: String,
    val team2: String,
    var startTime: Long,
    var isFavorite: Boolean = false,
    var remainingTime: Long = 0L
) : Parcelable