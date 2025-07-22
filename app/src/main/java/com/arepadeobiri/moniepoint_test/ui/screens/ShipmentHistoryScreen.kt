package com.arepadeobiri.moniepoint_test.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.arepadeobiri.moniepoint_test.data.SHIPMENTS
import com.arepadeobiri.moniepoint_test.data.Shipment
import com.arepadeobiri.moniepoint_test.data.ShipmentState
import com.arepadeobiri.moniepoint_test.ui.theme.Orange

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShipmentHistoryScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        "All" to null,
        "Completed" to ShipmentState.Completed,
        "In Progress" to ShipmentState.InProgress,
        "Pending" to ShipmentState.Pending,
        "Cancelled" to ShipmentState.Cancelled
    )


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
                    "Shipment history",
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
            ScrollableTabRow(
                containerColor = MaterialTheme.colorScheme.primary,
                selectedTabIndex = selectedTabIndex,
                edgePadding = 8.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Orange,
                        height = 4.dp
                    )
                }) {
                tabs.forEachIndexed { index, entry ->

                    val isSelected = index == selectedTabIndex

                    Tab(
                        selected = isSelected,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.alpha(if (isSelected) 1f else 0.6f)
                            ) {
                                Text(
                                    entry.first,
                                    style = TextStyle.Default.copy(color = Color.White)
                                )

                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(16.dp)
                                        )
                                        .background(
                                            color = if (isSelected) Orange else Color(
                                                0x55FFFFFF
                                            )
                                        )
                                        .padding(vertical = 4.dp, horizontal = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        SHIPMENTS.count { if (entry.second == null) true else it.state == entry.second }
                                            .toString(),
                                        style = TextStyle.Default.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            }
                        }
                    )
                }
            }

        }
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {

            item {
                Text(
                    "Shipments",
                    style = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))
            }



            itemsIndexed(SHIPMENTS.filter {
                val shipmentState = tabs[selectedTabIndex].second
                if (shipmentState == null) true else it.state == shipmentState
            }) { _: Int, item: Shipment ->

                var visible by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    visible = true
                }

                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight }, // from bottom
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight }, // slide up
                        animationSpec = tween(durationMillis = 300)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {

                        Column {
                            Text(
                                when (item.state) {
                                    ShipmentState.InProgress -> "In-progress"
                                    ShipmentState.Pending -> "Pending"
                                    ShipmentState.Loading -> "Loading"
                                    ShipmentState.Completed -> "Completed"
                                    ShipmentState.Cancelled -> "Canceled"
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0xFFEEEEEE))
                                    .padding(vertical = 4.dp, horizontal = 8.dp),
                                style = TextStyle.Default.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = when (item.state) {
                                        ShipmentState.InProgress -> Color.Magenta
                                        ShipmentState.Pending -> Orange
                                        ShipmentState.Loading -> Color.Blue
                                        ShipmentState.Completed -> Color(0xFF00cc00)
                                        ShipmentState.Cancelled -> Color.Red
                                    }
                                )

                            )

                            Spacer(Modifier.height(8.dp))
                            Text(
                                item.title,
                                style = TextStyle.Default.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                item.info,
                                style = TextStyle.Default.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            )

                            Spacer(Modifier.height(16.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    item.amount,
                                    style = TextStyle.Default.copy(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                )
                                Spacer(Modifier.width(4.dp))
                                Box(
                                    Modifier
                                        .width(4.dp)
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(color = Color(0xFFAAAAAA))
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    item.date,
                                    style = TextStyle.Default.copy(fontWeight = FontWeight.Medium)
                                )
                            }
                        }

                    }


                }
                Spacer(Modifier.height(16.dp))

            }
        }
    }
}