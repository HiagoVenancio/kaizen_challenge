package com.example.challenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.flow.Flow


@Dao
interface SportDao {
    @Query("SELECT * FROM sports where name != 'Unknown' order by id")
    fun getSports(): List<SportModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(breeds: List<SportModel>)

    @Query("UPDATE sports SET isExpanded = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)
}