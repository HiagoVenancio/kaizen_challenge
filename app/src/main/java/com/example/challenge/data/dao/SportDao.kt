package com.example.challenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challenge.domain.model.EventModel
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.flow.Flow


@Dao
interface SportDao {
    @Query("SELECT * FROM sports where name != 'Unknown' order by id")
    fun getSports(): Flow<List<SportModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(sports: List<SportModel>)

    @Query("UPDATE sports SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    @Query("UPDATE sports SET isExpanded = :isExpanded WHERE id = :id")
    suspend fun updateExpadedSection(id: String, isExpanded: Boolean)

    @Query("UPDATE sports SET events = :updatedEvents WHERE id = :sportId")
    suspend fun updateSportEvents(sportId: String, updatedEvents: List<EventModel>)

    @Query("SELECT * FROM sports WHERE id = :sportId")
    suspend fun getSportById(sportId: String): SportModel
}