package com.arepadeobiri.moniepoint_test.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arepadeobiri.moniepoint_test.ui.composables.Button
import com.arepadeobiri.moniepoint_test.ui.models.navigation.NavigationRoutes
import com.arepadeobiri.moniepoint_test.ui.theme.Green

@Composable
fun SuccessScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            "Total Estimated Amount",
            style = TextStyle.Default.copy(fontWeight = FontWeight.Medium, fontSize = 28.sp)
        )
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "$1460",
                style = TextStyle.Default.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Green
                )
            )
            Spacer(Modifier.width(4.dp))
            Text("USD", style = TextStyle.Default.copy(fontSize = 20.sp, color = Green))
        }

        Spacer(Modifier.height(16.dp))
        Text(
            "This amount is estimated. This will change if you change your location or weight",
            style = TextStyle.Default.copy(
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(24.dp))
        Button("Back to home") { navController.popBackStack(NavigationRoutes.Home.toString(),false) }
    }
}