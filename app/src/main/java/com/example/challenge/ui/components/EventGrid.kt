package com.example.challenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.challenge.domain.model.EventModel

@Composable
fun EventGrid(events: List<EventModel>, sportId: String, toggleEventClick: (String, String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(4.dp)
            .heightIn(max = 500.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(events) { index, event ->
            when (index) {
                0 -> event.startTime = 1736134160
            }
            EventItem(event = event, onClick = {
                toggleEventClick(sportId, event.id)
            })
        }
    }
}