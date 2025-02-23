package com.example.challenge.data.api.responses

import com.google.gson.annotations.SerializedName

data class SportsResponse(
    @SerializedName("i") val sportId: String?,
    @SerializedName("d") val sportName: Any?,
    @SerializedName("e") val events: List<EventResponse>?
)
