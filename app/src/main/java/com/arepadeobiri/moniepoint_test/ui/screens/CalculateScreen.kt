package com.arepadeobiri.moniepoint_test.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arepadeobiri.moniepoint_test.R
import com.arepadeobiri.moniepoint_test.data.CHIPS
import com.arepadeobiri.moniepoint_test.ui.composables.AnchoredDropdown
import com.arepadeobiri.moniepoint_test.ui.composables.Button
import com.arepadeobiri.moniepoint_test.ui.composables.Chip
import com.arepadeobiri.moniepoint_test.ui.composables.EditText
import com.arepadeobiri.moniepoint_test.ui.models.navigation.NavigationRoutes

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculateScreen(navController: NavController) {

    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var packaging by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {


                Text(
                    "Calculate",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle.Default.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(R.drawable.outline_keyboard_arrow_left_24),
                    contentDescription = null,
                    Modifier
                        .clickable { navController.navigateUp() }
                        .padding(top = 8.dp, bottom = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )

            }
        }
    }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .then(Modifier.padding(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    "Destination",
                    style = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    EditText(
                        senderLocation,
                        { senderLocation = it },
                        placeholder = "Sender location"
                    )
                    Spacer(Modifier.height(12.dp))

                    EditText(
                        receiverLocation,
                        { receiverLocation = it },
                        placeholder = "Receiver location"
                    )
                    Spacer(Modifier.height(12.dp))

                    EditText(weight, { weight = it }, placeholder = "Approx weight")
                }

                Spacer(Modifier.height(24.dp))
                Text(
                    "Packaging",
                    style = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(4.dp))
                Text(
                    "What are you sending?",
                    style = TextStyle.Default.copy(fontSize = 16.sp, color = Color.Gray)
                )

                Spacer(Modifier.height(16.dp))
                AnchoredDropdown(
                    listOf("Box", "Container", "Bubble wrap"),
                    { packaging = it },
                    packaging
                )



                Spacer(Modifier.height(24.dp))
                Text(
                    "Categories",
                    style = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(4.dp))
                Text(
                    "What are you sending?",
                    style = TextStyle.Default.copy(fontSize = 16.sp, color = Color.Gray)
                )

                Spacer(Modifier.height(16.dp))
                FlowRow {

                    CHIPS.forEach {
                        Chip(it, it == category, { category = it })
                        Spacer(Modifier.width(8.dp))
                    }

                }
            }

            Button(
                "Calculate",
                disabled = senderLocation.isBlank() || receiverLocation.isBlank() || packaging.isBlank() || category.isBlank()
            ) {
                navController.navigate(NavigationRoutes.Success.toString())
            }

        }
    }
}