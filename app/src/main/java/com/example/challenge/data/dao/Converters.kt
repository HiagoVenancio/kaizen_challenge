package com.example.challenge.data.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.challenge.domain.model.EventModel

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromEventList(events: List<EventModel>?): String {
        return gson.toJson(events)
    }

    @TypeConverter
    fun toEventList(json: String): List<EventModel> {
        val type = object : TypeToken<List<EventModel>>() {}.type
        return gson.fromJson(json, type)
    }
}
