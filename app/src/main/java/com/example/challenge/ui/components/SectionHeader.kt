package com.example.challenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.challenge.R

@Composable
fun SectionHeader(sportName: String, isExpanded: Boolean, onToggleExpand: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(8.dp)
            .clickable { onToggleExpand() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            style = MaterialTheme.typography.titleLarge,
            text = sportName,
            modifier = Modifier
                .weight(0.9f)
                .padding(8.dp)

        )

        IconFromDrawable(
            drawable = R.drawable.star_border,
            modifier = Modifier
                .size(30.dp)
                .weight(0.2f)
                .clickable {

                }
        )

        IconFromDrawable(
            if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down,
            modifier = Modifier
                .size(40.dp)
                .weight(0.2f)
        )
    }
}