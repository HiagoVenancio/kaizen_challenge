package com.example.challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.data.repository.MainDataRepository
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(private val repository: MainDataRepository) : ViewModel() {
    private val _mainData = MutableStateFlow<List<SportModel>>(emptyList())
    val mainData: StateFlow<List<SportModel>> = _mainData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchMainData()
    }

    fun fetchMainData() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _mainData.value = repository.getAllSports()
            } catch (e: SocketTimeoutException) {
                _errorMessage.value =
                    "The request timed out. Please check your internet connection."
            } catch (e: IOException) {
                _errorMessage.value = "No internet connection. Please try again."
            } catch (e: UnknownHostException) {
                _errorMessage.value = "Network issue detected. Please check your connection."
            } catch (e: Exception) {
                _errorMessage.value = "Unknown error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleExpand(sportId: String) {
        _mainData.value = _mainData.value.map { sport ->
            if (sport.id == sportId) sport.copy(isExpanded = !sport.isExpanded)
            else sport
        }
    }

    fun toggleFavoriteEvent(sportId: String, eventId: String) {
        _mainData.value = _mainData.value.map { sport ->
            if (sport.id == sportId) {
                sport.copy(
                    events = sport.events.map { event ->
                        if (event.id == eventId) {
                            event.copy(isFavorite = !event.isFavorite)
                        } else {
                            event
                        }
                    }
                )
            } else {
                sport
            }
        }
    }

}