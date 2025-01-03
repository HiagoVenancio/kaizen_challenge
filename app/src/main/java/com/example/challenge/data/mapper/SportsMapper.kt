package com.example.challenge.data.mapper

import com.example.challenge.data.api.responses.EventResponse
import com.example.challenge.data.api.responses.SportsResponse
import com.example.challenge.domain.model.SportModel
import com.example.challenge.domain.model.EventModel


fun SportsResponse.toDomainModel(): SportModel {
    return SportModel(
        id = this.sportId ?: "",
        name = when (this.sportName) {
            is String -> this.sportName
            else -> "Unknown"
        },
        events = this.events?.map { it.toDomainModel() } ?: emptyList()
    )
}

fun EventResponse.toDomainModel(): EventModel {
    val teams = this.eventName.split("-").map { it.trim() }
    return EventModel(
        id = this.eventId,
        team1 = teams.getOrNull(0) ?: "Unknown",
        team2 = teams.getOrNull(1) ?: "Unknown",
        startTime = this.eventStartTime,
        isFavorite = false,
        remainingTime = 0L
    )
}



