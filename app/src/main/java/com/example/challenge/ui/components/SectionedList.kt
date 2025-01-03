package com.example.challenge.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.challenge.domain.model.SportModel

@Composable
fun SectionedList(
    sports: List<SportModel>,
    onToggleFavoriteSection: (SportModel) -> Unit,
    onToggleFavoriteEvent: (String, String) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        sports.forEach { sport ->
            item {
                val isExpanded = rememberSaveable(sport.id) { mutableStateOf(false) }
                Row(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    SectionHeader(
                        sport = sport,
                        isExpanded = isExpanded.value,
                        onToggleExpand = {
                            isExpanded.value = !isExpanded.value
                        },
                        onToggleFavoriteSection = { onToggleFavoriteSection(sport) }
                    )
                }
                if (isExpanded.value) {
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


