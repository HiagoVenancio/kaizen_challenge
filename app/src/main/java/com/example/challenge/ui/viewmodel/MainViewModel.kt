package com.example.challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.data.repository.ISportRepository
import com.example.challenge.data.repository.SportRepository
import com.example.challenge.domain.model.SportModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(private val repository: ISportRepository) : ViewModel() {
    private val _isFilterActive = MutableStateFlow(false)
    val isFilterActive: StateFlow<Boolean> = _isFilterActive

    val filteredData: StateFlow<List<SportModel>> =
        _isFilterActive.flatMapLatest { isFilterActive ->
            if (isFilterActive) {
                repository.getFavoriteSportsSections()
            } else {
                repository.getAllSports()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteItems: StateFlow<List<SportModel>> = repository.getFavoriteItems()
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

    fun filterFavoriteSports(): List<SportModel> {
        return favoriteItems.value.filter { it.isFavorite }
    }

    fun toggleFilter() {
        _isFilterActive.value = !_isFilterActive.value
    }

}