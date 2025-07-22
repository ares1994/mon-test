package com.arepadeobiri.moniepoint_test.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arepadeobiri.moniepoint_test.ui.theme.Orange

@Composable
fun Button(
    title: String,
    disabled: Boolean = false,
    onPress: () -> Unit
) {
    TextButton(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .alpha(if (disabled) .4f else 1f)
            .background(color = Orange)
            .padding(vertical = 4.dp),
        onClick = if (disabled) fun() {} else onPress,
    ) {
        Text(
            title,
            style = TextStyle.Default.copy(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
