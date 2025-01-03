package com.example.challenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
 /*   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEvent: FavoriteEvent)*/

   /* @Query("DELETE FROM favorites WHERE eventId = :eventId")
    suspend fun removeFavorite(eventId: String)*/

/*    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEvent>*/
}
