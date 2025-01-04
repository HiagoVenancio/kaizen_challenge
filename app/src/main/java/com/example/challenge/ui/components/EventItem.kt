package com.example.challenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.challenge.R
import com.example.challenge.domain.model.EventModel
import kotlinx.coroutines.delay

@Composable
fun EventItem(event: EventModel, onClick: () -> Unit, isFromFavorite: Boolean = false) {
    val currentTime = System.currentTimeMillis() / 1000
    var remainingTime by remember { mutableLongStateOf(event.startTime - currentTime) }

    LaunchedEffect(event.startTime) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }

    Card(
        modifier = Modifier
            .padding(2.dp)
            .height(150.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            TextSingleLine(
                value = if (remainingTime > 0) formatTime(remainingTime) else stringResource(
                    R.string.already_started_message
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            IconFromDrawable(
                if (event.isFavorite) R.drawable.star_filled else R.drawable.star_border,
                tintColor = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextSingleLine(value = event.team1)

            IconFromDrawable(
                R.drawable.versus,
                modifier = Modifier.size(20.dp),
                tintColor = MaterialTheme.colorScheme.primary,
            )

            TextSingleLine(value = event.team2)

            if (isFromFavorite) {
                Spacer(modifier = Modifier.padding(4.dp))
                TextSingleLineBackground(
                    value = event.sportName,
                    MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

fun formatTime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d:%02d".format(hours, minutes, remainingSeconds)
}
