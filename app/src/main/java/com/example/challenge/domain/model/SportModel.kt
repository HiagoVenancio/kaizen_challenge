package com.example.challenge.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challenge.domain.model.SportModel.Companion.SPORTS_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = SPORTS_TABLE)
data class SportModel(
    @PrimaryKey val id: String,
    val name: String,
    val events: List<SportEvent> = mutableListOf(),
    var isExpanded: Boolean = false
) : Parcelable {

    companion object {
        const val SPORTS_TABLE = "sports"
    }
}
