package com.example.challenge.data.mapper

import com.example.challenge.data.api.model.EventResponse
import com.example.challenge.data.api.model.SportsResponse
import com.example.challenge.domain.model.SportModel
import com.example.challenge.domain.model.SportEvent


fun SportsResponse.toDomainModel(): SportModel {
    return SportModel(
        id = this.sportId ?: "",
        name = when (this.sportName) {
            is String -> this.sportName
            //is List<*> -> (this.sportName as List<*>).joinToString(", ") { it.toString() }
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



