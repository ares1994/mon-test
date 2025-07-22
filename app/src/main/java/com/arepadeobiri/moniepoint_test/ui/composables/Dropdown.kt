package com.arepadeobiri.moniepoint_test.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arepadeobiri.moniepoint_test.R


@Composable
fun Dropdown(
    options: List<String>,
    onSetValue: (value: String) -> Unit,
    value: String
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Anchor Composable
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size // capture the size of the anchor
                }
                .clickable { expanded = true }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(24.dp))
            Box(
                Modifier
                    .alpha(.4f)
                    .fillMaxHeight()
                    .background(Color.Gray)
                    .width(1.dp)
            )
            Spacer(Modifier.width(8.dp))

            Text(
                text = value.ifBlank { "Select option" },
                modifier = Modifier.weight(1f),
                style = TextStyle.Default.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),

                )
            Icon(
                painter = painterResource(R.drawable.outline_keyboard_arrow_down_24),
                tint = Color.Gray,
                contentDescription = null
            )
        }

        // DropdownMenu anchored to above Row
        DropdownMenu(
            containerColor = Color.White,
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }) // match anchor width
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            label,
                            style = TextStyle.Default.copy(
                                fontSize = 16.sp
                            ),
                        )
                    },
                    onClick = {
                        expanded = false
                        onSetValue(label)
                    }
                )
            }
        }
    }
}
