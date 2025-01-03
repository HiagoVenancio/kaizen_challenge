package com.example.challenge.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.challenge.R
import com.example.challenge.ui.components.IconFromDrawable
import com.example.challenge.ui.components.SectionedList
import com.example.challenge.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavHostController, viewModel: MainViewModel) {
    val favorites by viewModel.favoriteItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.farovite_title)) },
                navigationIcon = {
                    IconFromDrawable(
                        R.drawable.arrow_back,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SectionedList(
                sports = favorites,
                onToggleFavoriteSection = { sport ->
                    viewModel.toggleFavoriteSection(sport)
                },
                onToggleFavoriteEvent = { sportId, eventId ->
                    viewModel.toggleFavoriteEvent(sportId, eventId)
                }
            )
        }
    }
}