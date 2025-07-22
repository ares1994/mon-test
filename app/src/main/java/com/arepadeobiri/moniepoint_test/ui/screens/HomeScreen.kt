package com.arepadeobiri.moniepoint_test.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arepadeobiri.moniepoint_test.R
import com.arepadeobiri.moniepoint_test.data.SEARCH_ITEMS
import com.arepadeobiri.moniepoint_test.ui.theme.Orange


@Composable
fun HomeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(if (isVisible) 16.dp else 4.dp))

                AnimatedVisibility(
                    visible = !isVisible,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally()
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.outline_keyboard_arrow_left_24),
                            contentDescription = null,
                            Modifier
                                .clickable {
                                    isVisible = !isVisible; focusManager.clearFocus(); searchText =
                                    ""
                                }
                                .padding(top = 8.dp, bottom = 8.dp),
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )

                    }
                }


                Column(modifier = Modifier.weight(1f)) {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = expandVertically(), // Optional: animation for appearing
                        exit = shrinkVertically()
                    ) {
                        Column {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .width(40.dp)
                                        .height(40.dp)
                                        .background(color = MaterialTheme.colorScheme.background)
                                ) { }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(modifier = Modifier.alpha(.8f)) {
                                        Image(
                                            modifier = Modifier.height(16.dp),
                                            painter = painterResource(R.drawable.locator),
                                            contentDescription = "Locator",
                                            colorFilter = ColorFilter.tint(color = Color.White)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            "Your location",
                                            style = TextStyle.Default.copy(color = Color.White)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row {
                                        Text(
                                            "Wertheimer, Illinois",
                                            style = TextStyle.Default.copy(
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .width(40.dp)
                                        .height(40.dp)
                                        .background(color = MaterialTheme.colorScheme.background),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(painter = painterResource(R.drawable.outline_notifications_24), contentDescription = null)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color.White)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_search_24),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                contentDescription = null
                            )


                            Spacer(Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)

                            ) {
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Enter the receipt number...",
                                        style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                                    )
                                }

                                BasicTextField(

                                    value = searchText,
                                    maxLines = 1,
                                    onValueChange = { searchText = it },
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    ),

                                    modifier = Modifier
                                        .height(IntrinsicSize.Min)
                                        .padding(0.dp)
                                        .fillMaxWidth()
                                        .onFocusChanged {
                                            isVisible = !it.hasFocus
                                        }
                                )
                            }
                            Spacer(Modifier.width(8.dp))

                        }
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Orange)
                        ) { }
                    }


                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )
        }
    }) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .then(Modifier.padding(vertical = 16.dp))
        ) {


            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(), // Optional: animation for appearing
                exit = fadeOut()
            ) {
                Column {
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            "Tracking",
                            style = TextStyle.Default.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Card(
                            colors = CardDefaults.cardColors().copy(containerColor = Color.White),
                            modifier = Modifier.clip(
                                RoundedCornerShape(16.dp)
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(
                                    Modifier
                                        .weight(1f)
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        "Shipment number",
                                        style = TextStyle.Default.copy(color = Color.Gray)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        "NEJ20089934122231",
                                        style = TextStyle.Default.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp
                                        )
                                    )

                                }
                            }

                            Spacer(
                                Modifier
                                    .alpha(.2f)
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(color = Color.Gray)
                            )

                            Row(Modifier.padding(16.dp)) {
                                Column(Modifier.weight(1f)) {

                                    Row(verticalAlignment = Alignment.CenterVertically) {

                                        Box(
                                            Modifier
                                                .height(32.dp)
                                                .width(32.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(MaterialTheme.colorScheme.primary)
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        Column {
                                            Text(
                                                "Sender",
                                                style = TextStyle.Default.copy(color = Color.Gray)
                                            )
                                            Spacer(Modifier.height(2.dp))
                                            Text(
                                                "Atlanta, 5243",
                                                style = TextStyle.Default.copy(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                    }
                                    Spacer(Modifier.height(32.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {

                                        Box(
                                            Modifier
                                                .height(32.dp)
                                                .width(32.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(MaterialTheme.colorScheme.primary)
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        Column {
                                            Text(
                                                "Receiver",
                                                style = TextStyle.Default.copy(color = Color.Gray)
                                            )
                                            Spacer(Modifier.height(2.dp))
                                            Text(
                                                "Chicago, 6342",
                                                style = TextStyle.Default.copy(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                    }

                                }



                                Column {
                                    Column {
                                        Text(
                                            "Time",
                                            style = TextStyle.Default.copy(color = Color.Gray)
                                        )
                                        Spacer(Modifier.height(2.dp))
                                        Text(
                                            "2 days - 3 days",
                                            style = TextStyle.Default.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                    }
                                    Spacer(Modifier.height(32.dp))
                                    Column {
                                        Text(
                                            "Status",
                                            style = TextStyle.Default.copy(color = Color.Gray)
                                        )
                                        Spacer(Modifier.height(2.dp))
                                        Text(
                                            "Waiting to collect",
                                            style = TextStyle.Default.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(
                                Modifier
                                    .alpha(.2f)
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(color = Color.Gray)
                            )


                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {


                                Image(
                                    painter = painterResource(R.drawable.outline_add_24),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(2.dp))
                                Text(
                                    "Add Stop",
                                    style = TextStyle.Default.copy(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Medium
                                    )
                                )

                            }


                        }
                        Spacer(Modifier.height(24.dp))
                        Text(
                            "Available vehicles",
                            style = TextStyle.Default.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(Modifier.height(8.dp))


                    }
                    LazyRow(modifier = Modifier.fillMaxWidth(), state = rememberLazyListState()) {


                        itemsIndexed(listOf(1, 2, 3, 4)) { index, item ->
                            if (index == 0) Spacer(Modifier.width(16.dp))
                            Row {
                                Column(
                                    Modifier
                                        .width(150.dp)
                                        .height(250.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(Color.White)
                                ) {

                                    Column(Modifier.padding(16.dp)) {
                                        Text(
                                            "Ocean Freight",
                                            style = TextStyle.Default.copy(fontSize = 16.sp)
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            "International",
                                            style = TextStyle.Default.copy(Color.Gray)
                                        )
                                    }

                                }
                                Spacer(Modifier.width(16.dp))
                            }
                        }
                    }
                }
            }




            if (!isVisible)
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    AnimatedVisibility(
                        visible = !isVisible,
                        enter = slideInVertically(

                        ), // Optional: animation for appearing
                        exit = fadeOut()
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth()

                                .clip(
                                    RoundedCornerShape(16.dp)
                                )
                                .background(color = Color.White)
                                .padding(horizontal = 16.dp)
                        ) {

                            SEARCH_ITEMS.filter {
                                it.first.contains(
                                    searchText,
                                    true
                                ) || it.second.contains(searchText, true)
                            }.map {
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
                                        modifier = Modifier.padding(vertical = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(MaterialTheme.colorScheme.primary)
                                        ) {

                                        }

                                        Spacer(Modifier.width(8.dp))
                                        Column(Modifier.weight(1f)) {
                                            Text(
                                                it.first,
                                                style = TextStyle.Default.copy(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                            Spacer(Modifier.height(4.dp))
                                            Text(
                                                it.second,
                                                style = TextStyle.Default.copy(
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                            )
                                        }
                                    }
                                }


                            }

                        }
                    }


                }


        }


    }
}
