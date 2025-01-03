package com.example.challenge.data.mapper

import com.example.challenge.data.model.EventResponse
import com.example.challenge.data.model.SportsResponse
import com.example.challenge.domain.model.Sport
import com.example.challenge.domain.model.SportEvent


fun SportsResponse.toDomainModel(): Sport {
    return Sport(
        id = this.sportId ?: "",
        name = when (this.sportName) {
            is String -> this.sportName
            is List<*> -> (this.sportName as List<*>).joinToString(", ") { it.toString() }
            else -> "Unknown"
        },
        events = this.events?.map { it.toDomainModel() } ?: emptyList()
    )
}

fun EventResponse.toDomainModel(): SportEvent {
    val teams = this.eventName.split("-").map { it.trim() }
    return SportEvent(
        id = this.eventId,
        team1 = teams.getOrNull(0) ?: "Unknown",
        team2 = teams.getOrNull(1) ?: "Unknown",
        startTime = this.eventStartTime,
        isFavorite = false,
        remainingTime = 0L
    )
}


