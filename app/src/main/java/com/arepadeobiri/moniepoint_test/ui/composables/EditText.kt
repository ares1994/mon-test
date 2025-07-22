package com.arepadeobiri.moniepoint_test.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EditText(
    value: String,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int? = null,
    disabled: Boolean = false,
    leftComposable: (@Composable () -> Unit)? = null,
    rightComposable: (@Composable () -> Unit)? = null
) {


    Column(modifier = modifier) {
        if (!label.isNullOrBlank()) {
            Text(
                label,
                style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.outlineVariant),
            )
            Spacer(Modifier.height(4.dp))
        }
        TextField(
            value,
            {
                if (maxLength != null && it.length > maxLength) {
                    return@TextField
                }
                onValueChange(it)
            },

            trailingIcon = {
                rightComposable?.invoke()
            },
            leadingIcon = leftComposable,
            maxLines = 1,
            enabled = !disabled,
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            placeholder = {
                Text(
                    placeholder,
                    style = TextStyle.Default.copy(color = Color.Gray, fontSize = 16.sp),
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),

            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MaterialTheme.colorScheme.outline,
                errorIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )

    }


}