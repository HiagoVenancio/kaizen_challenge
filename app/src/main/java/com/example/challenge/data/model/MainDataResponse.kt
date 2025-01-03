package com.example.challenge.data.model

import com.google.gson.annotations.SerializedName

data class SportsResponse(
    @SerializedName("i") val sportId: String?,
    @SerializedName("d") val sportName: Any?,
    @SerializedName("e") val events: List<Event>?
)

data class Event(
    @SerializedName("i") val eventId: String,
    @SerializedName("si") val sportId: String,
    @SerializedName("d") val eventName: String,
    @SerializedName("tt") val eventStartTime: Long
)