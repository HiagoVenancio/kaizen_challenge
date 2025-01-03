package com.example.challenge.data.repository

import android.annotation.SuppressLint
import com.example.challenge.data.api.DataApi
import com.example.challenge.data.dao.SportDao
import com.example.challenge.data.mapper.toDomainModel
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@SuppressLint("NewApi")
class SportRepository(private val api: DataApi, private val sportsDao: SportDao) {

    suspend fun getNewSports() = withContext(Dispatchers.IO) {
        val result = api.getMainData()
        val sports = result.map { it.toDomainModel() }
        sportsDao.insertAll(sports)
    }

    fun getAllSports() = sportsDao.getSports()

    fun getFavoriteItems(): Flow<List<SportModel>> = sportsDao.getSports().map { sports ->
        sports.mapNotNull { sport ->
            val favoriteEvents = sport.events.filter { it.isFavorite }
            if (favoriteEvents.isNotEmpty()) {
                sport.copy(events = favoriteEvents)
            } else null
        }
    }

    suspend fun toggleFavoriteSection(sports: SportModel) = withContext(Dispatchers.IO) {
        try {
            sportsDao.updateFavoriteStatus(sports.id, !sports.isFavorite)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateSportEvents(sportId: String, eventId: String) = withContext(Dispatchers.IO) {
        val sport = sportsDao.getSportById(sportId)

        val updatedEvents = sport.events.map { event ->
            if (event.id == eventId) {
                event.copy(isFavorite = !event.isFavorite)
            } else {
                event
            }
        }
        sportsDao.updateSportEvents(sportId, updatedEvents)
    }
}