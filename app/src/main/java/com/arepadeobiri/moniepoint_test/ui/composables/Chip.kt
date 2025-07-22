package com.arepadeobiri.moniepoint_test.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Chip(
    title: String,
    isChecked: Boolean,
    onSetChecked: (title: String) -> Unit
) {

    FilterChip(
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = Color.Black,
            selectedLabelColor = Color.White,
            selectedLeadingIconColor = Color.White
        ),
        selected = isChecked,
        onClick = { onSetChecked(title) },
        label = {
            Text(
                title,
                style = TextStyle.Default.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        },
        leadingIcon = if (isChecked) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        } else null
    )
}