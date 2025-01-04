package com.example.challenge.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconFromDrawable(
    drawable: Int,
    modifier: Modifier = Modifier.size(25.dp),
    tintColor: Color = Color.Black,
    contentDescription: String = ""
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = drawable),
        contentDescription = contentDescription,
        tint = tintColor
    )
}