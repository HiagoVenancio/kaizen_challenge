package com.example.challenge.data.mapper

import com.example.challenge.data.api.responses.EventResponse
import com.example.challenge.data.api.responses.SportsResponse
import com.example.challenge.domain.model.SportModel
import com.example.challenge.domain.model.EventModel


fun SportsResponse.toDomainModel(): SportModel {
    val name = when (this.sportName) {
        is String -> this.sportName
        else -> "Unknown"
    }
    return SportModel(
        id = this.sportId ?: "",
        name = name,
        events = this.events?.map { it.toDomainModel(name) } ?: emptyList()
    )
}

fun EventResponse.toDomainModel(eventName: String): EventModel {
    val teams = this.eventName.split("-").map { it.trim() }
    return EventModel(
        id = this.eventId,
        sportId = this.sportId,
        sportName = eventName,
        team1 = teams.getOrNull(0) ?: "",
        team2 = teams.getOrNull(1) ?: "",
        startTime = this.eventStartTime,
        isFavorite = false,
        remainingTime = 0L
    )
}



