package com.example.challenge.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.challenge.domain.model.SportModel

@Composable
fun SectionedList(
    sports: List<SportModel>,
    onToggleFavoriteSection: (SportModel) -> Unit,
    onToggleFavoriteEvent: (String, String) -> Unit,
    onToggleExpand: (SportModel) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        sports.forEach { sport ->
            item {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    SectionHeader(
                        sport = sport,
                        onToggleExpand = { onToggleExpand(sport) },
                        onToggleFavoriteSection = { onToggleFavoriteSection(sport) }
                    )
                }
            }

            if (sport.isExpanded) {
                item {
                    EventGrid(
                        events = sport.events,
                        sportId = sport.id,
                        toggleEventClick = onToggleFavoriteEvent
                    )
                }
            }
        }
    }
}


