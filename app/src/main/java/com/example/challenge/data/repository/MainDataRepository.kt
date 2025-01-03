package com.example.challenge.data.repository

import android.annotation.SuppressLint
import com.example.challenge.data.api.DataApi
import com.example.challenge.data.dao.SportDao
import com.example.challenge.data.mapper.toDomainModel
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("NewApi")
class MainDataRepository(private val api: DataApi, val sportsDao: SportDao) {

    suspend fun getAllSports() = withContext(Dispatchers.IO) {
        val result = api.getMainData()
        val sports = result.map {
            it.toDomainModel()
        }
        sportsDao.insertAll(sports)
        sportsDao.getSports()
    }

    suspend fun toggleFavorite(sports: SportModel) = withContext(Dispatchers.IO) {
        try {
            sportsDao.updateFavoriteStatus(sports.id, !sports.isExpanded)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        sportsDao.getSports()
    }
}