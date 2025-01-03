package com.example.challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.data.repository.SportRepository
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(private val repository: SportRepository) : ViewModel() {

    val mainData: StateFlow<List<SportModel>> = repository.getAllSports()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

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
                repository.getNewSports()

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

    fun toggleExpand(sport: SportModel) {
        viewModelScope.launch {
            repository.toggleSectionExpand(sport)
        }
    }

    fun toggleFavoriteSection(sports: SportModel) {
        viewModelScope.launch {
            repository.toggleFavoriteSection(sports)
        }
    }

    fun toggleFavoriteEvent(sportId: String, eventId: String) {
        viewModelScope.launch {
            repository.updateSportEvents(sportId, eventId)
        }
    }

}