package com.example.challenge.data.api.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("i") val eventId: String,
    @SerializedName("si") val sportId: String,
    @SerializedName("d") val eventName: String,
    @SerializedName("tt") val eventStartTime: Long
)