package com.arepadeobiri.moniepoint_test.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arepadeobiri.moniepoint_test.R
import com.arepadeobiri.moniepoint_test.data.NavigationRoutes
import com.arepadeobiri.moniepoint_test.ui.screens.CalculateScreen
import com.arepadeobiri.moniepoint_test.ui.screens.HomeScreen
import com.arepadeobiri.moniepoint_test.ui.screens.ShipmentHistoryScreen
import com.arepadeobiri.moniepoint_test.ui.screens.SuccessScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var showTab by remember { mutableStateOf(true) }


    LaunchedEffect(currentDestination?.route) {
        currentDestination?.route?.let { route ->
            showTab = route == NavigationRoutes.Home.toString()
            when (route) {
                NavigationRoutes.Shipment.toString() -> {
                    selectedTab = 2
                }

                NavigationRoutes.Home.toString() -> {
                    selectedTab = 0
                }

                NavigationRoutes.Calculate.toString() -> {
                    selectedTab = 1
                }
            }
        }
    }


    val tabItems = listOf("Home", "Calculate", "Shipment", "Profile")
    val tabIcons = listOf(
        painterResource(R.drawable.home),
        painterResource(R.drawable.calculate),
        painterResource(R.drawable.clock),
        painterResource(R.drawable.person)
    )


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val tabCount = tabItems.size
    val tabWidth = screenWidth / tabCount

    val indicatorOffset by animateDpAsState(
        targetValue = tabWidth * selectedTab,
        animationSpec = tween(durationMillis = 250),
        label = "indicatorOffset"
    )

    Scaffold(
        bottomBar = {

            AnimatedVisibility(
                visible = showTab,
                enter = slideInVertically(),
                exit = fadeOut()
            ) {
                BottomAppBar(containerColor = Color.White) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        // Animated indicator
                        Box(
                            modifier = Modifier
                                .offset(x = indicatorOffset)
                                .width(tabWidth)
                                .height(4.dp)
                                .align(Alignment.TopStart)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
//                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            tabItems.forEachIndexed { index, label ->
                                Column(
                                    modifier = Modifier
                                        .clickable {
                                            when (index) {
                                                1 -> navController.navigate(NavigationRoutes.Calculate.toString())
                                                2 -> navController.navigate(NavigationRoutes.Shipment.toString())
                                            }
                                        }
                                        .width(tabWidth)
                                        .padding(vertical = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp),
                                        painter = tabIcons[index],
                                        contentDescription = label,
                                        tint = if (selectedTab == index)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = label,
                                        fontSize = 12.sp,
                                        color = if (selectedTab == index)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    ) { _ ->

        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Home.toString()
        ) {
            composable(NavigationRoutes.Home.toString()) {
                HomeScreen(
                    navController
                )
            }

            composable(NavigationRoutes.Success.toString()) {
                SuccessScreen(
                    navController
                )
            }


            composable(NavigationRoutes.Shipment.toString()) {
                ShipmentHistoryScreen(
                    navController
                )
            }

            composable(NavigationRoutes.Calculate.toString()) {
                CalculateScreen(
                    navController
                )
            }


        }
    }

}