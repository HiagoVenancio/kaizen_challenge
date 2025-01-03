package com.example.challenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextSingleLine(value: String) {
    Text(
        textAlign = TextAlign.Center,
        maxLines = 1,
        style = MaterialTheme.typography.bodySmall,
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    )
}

