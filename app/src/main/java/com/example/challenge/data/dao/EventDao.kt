package com.example.challenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface EventDao {
    /*@Query("SELECT * FROM cat_breeds order by name")
    fun getCatBreeds(): Flow<List<CatBreedUIModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(breeds: List<Eve>)
*/
    /*

        @Query("UPDATE cat_breeds SET isFavorite = :isFavorite WHERE id = :id")
        suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)

        @Query("SELECT * FROM cat_breeds WHERE isFavorite = 1")
        fun getFavoriteCatBreeds(): Flow<List<CatBreedUIModel>>

        @Query("SELECT isFavorite FROM cat_breeds WHERE id = :id")
        suspend fun isFavorite(id: String): Boolean*/
}