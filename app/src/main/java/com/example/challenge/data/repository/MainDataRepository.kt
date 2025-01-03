package com.example.challenge.data.repository

import android.annotation.SuppressLint
import com.example.challenge.data.api.DataApi
import com.example.challenge.data.mapper.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("NewApi")
class MainDataRepository(private val api: DataApi) {

    suspend fun getMainData() = withContext(Dispatchers.IO) {
        val result = api.getMainData()
        result.map { it.toDomainModel() }
    }
}