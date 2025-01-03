package com.example.challenge.data.api

import com.example.challenge.data.api.model.SportsResponse
import retrofit2.http.GET

interface DataApi {
    @GET("api/sports")
    suspend fun getMainData(): List<SportsResponse>
}
