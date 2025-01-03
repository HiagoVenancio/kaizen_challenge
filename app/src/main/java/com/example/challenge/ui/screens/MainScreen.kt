package com.example.challenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.challenge.R
import com.example.challenge.ui.components.ErrorView
import com.example.challenge.ui.components.SectionedList
import com.example.challenge.ui.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val mainData by viewModel.mainData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.main_title)) },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    ErrorView(errorMessage = errorMessage.toString()) {
                        viewModel.fetchMainData()
                    }
                }

                else -> {
                    SectionedList(
                        sports = mainData,
                        onToggleFavoriteSection = { sport ->
                            viewModel.toggleFavoriteSection(sport)
                        },
                        onToggleFavoriteEvent = { sportId, eventId ->
                            viewModel.toggleFavoriteEvent(sportId, eventId)
                        },
                        onToggleExpand = { sport -> viewModel.toggleExpand(sport) }
                    )
                }
            }
        }
    }
}
