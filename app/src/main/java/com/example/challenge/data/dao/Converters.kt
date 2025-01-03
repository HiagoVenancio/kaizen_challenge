package com.example.challenge.data.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.challenge.domain.model.SportEvent

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromEventList(events: List<SportEvent>?): String {
        return gson.toJson(events)
    }

    @TypeConverter
    fun toEventList(json: String): List<SportEvent> {
        val type = object : TypeToken<List<SportEvent>>() {}.type
        return gson.fromJson(json, type)
    }
}
