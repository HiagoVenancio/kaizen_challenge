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

interface ISportRepository {
    suspend fun getNewSports()
    fun getAllSports(): Flow<List<SportModel>>
    fun getFavoriteSportsSections(): Flow<List<SportModel>>
    fun getFavoriteItems(): Flow<List<SportModel>>
    suspend fun toggleFavoriteSection(sports: SportModel)
    suspend fun updateSportEvents(sportId: String, eventId: String)
}

@SuppressLint("NewApi")
class SportRepository(private val api: DataApi, private val sportsDao: SportDao) :
    ISportRepository {

    override suspend fun getNewSports() = withContext(Dispatchers.IO) {
        val result = api.getMainData()
        val sports = result.map { it.toDomainModel() }
        sportsDao.insertAll(sports)
    }

    override fun getAllSports() = sportsDao.getSports()

    override fun getFavoriteSportsSections(): Flow<List<SportModel>> =
        sportsDao.getSports().map { sports ->
            sports.filter { it.isFavorite }
        }

    override fun getFavoriteItems(): Flow<List<SportModel>> = sportsDao.getSports().map { sports ->
        sports.mapNotNull { sport ->
            val favoriteEvents = sport.events.filter { it.isFavorite }
            if (favoriteEvents.isNotEmpty()) {
                sport.copy(events = favoriteEvents)
            } else null
        }
    }

    override suspend fun toggleFavoriteSection(sports: SportModel) = withContext(Dispatchers.IO) {
        try {
            sportsDao.updateFavoriteSection(sports.id, !sports.isFavorite)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun updateSportEvents(sportId: String, eventId: String) =
        withContext(Dispatchers.IO) {
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