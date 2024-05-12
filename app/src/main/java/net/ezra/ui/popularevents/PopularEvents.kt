package net.ezra.ui.popularevents


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_POPULAREVENTS


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularEventsScreen(navController: NavHostController) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Text(text = "Popular Events",
                            fontFamily = FontFamily.Serif)

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_POPULAREVENTS) { inclusive = true }
                        }
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "",
                            Modifier.size(40.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Notifications, contentDescription = "" )
                    }
                },

                colors = TopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.DarkGray,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.Gray,
                    actionIconContentColor = Color.Transparent
                )

            )
        },

        content = {


            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background6), contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )

            }


            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp)
            ) {

                item {

                    Spacer(modifier = Modifier.height(70.dp))

                    Card(onClick = { /*TODO*/ }) {

                        Column (
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ){

                                Text(text = "Title")

                                Text(text = "Time")

                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                                horizontalArrangement = Arrangement.End
                            ){

                                Text(text = "Date")

                            }

                        }

                        Column {
                            Image(painter = painterResource(id = R.drawable.cabin2), contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop)

                            Column (modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {

                                Text(text = "Title", fontSize = 22.sp,
                                    fontFamily = FontFamily.Serif )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Description", fontSize = 22.sp,
                                    fontFamily = FontFamily.Serif )

                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Card(onClick = { /*TODO*/ }) {

                        Column (
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ){

                                Text(text = "Title")

                                Text(text = "Time")

                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                                horizontalArrangement = Arrangement.End
                            ){

                                Text(text = "Date")

                            }

                        }

                        Column {
                            Image(painter = painterResource(id = R.drawable.cabin2), contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp, end = 15.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop)

                            Column (modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {

                                Text(text = "Title", fontSize = 22.sp,
                                    fontFamily = FontFamily.Serif )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "Description", fontSize = 22.sp,
                                    fontFamily = FontFamily.Serif )

                            }
                        }

                    }



                }

            }
        },


//        bottomBar = { BottomBar(navController) }
    )



}

//@SuppressLint("AutoboxingStateCreation")
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val selectedIndex = remember { mutableStateOf(0) }
//    BottomNavigation(
//        elevation = 10.dp,
//        backgroundColor = Color.White,
//        modifier = Modifier
//            .padding()
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
//            .size(height = 60.dp, width = 100.dp)
//    ){
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.TwoTone.Home,"",
//                tint = Color.Gray,
//                modifier = Modifier
//                    .size(35.dp))
//        },
//            label = { Text(text = "",
//                color = Color.Gray) }, selected = (selectedIndex.value == 0), onClick = {
//                navController.navigate(ROUTE_HOME) {
//                    popUpTo(ROUTE_FAVOURITE) { inclusive = true }
//                }
//            },
//            selectedContentColor = Color.DarkGray)
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.TwoTone.Favorite,"",
//                tint = Color.Gray,
//                modifier = Modifier
//                    .size(35.dp))
//        },
//            label = { Text(text = "",
//                color = Color.Gray) }, selected = (selectedIndex.value == 1), onClick = {
//                navController.navigate(ROUTE_FAVOURITE) {
//                    popUpTo(ROUTE_FAVOURITE) { inclusive = true }
//                }
//            },
//            selectedContentColor = Color.DarkGray)
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.TwoTone.DateRange,"",
//                tint = Color.Gray,
//                modifier = Modifier
//                    .size(35.dp))
//        },
//            label = { Text(text = "",
//                color = Color.Gray) }, selected = (selectedIndex.value == 1), onClick = {
//                navController.navigate(ROUTE_CREATE) {
//                    popUpTo(ROUTE_FAVOURITE) { inclusive = true }
//                }
//            },
//            selectedContentColor = Color.DarkGray)
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.TwoTone.Person, "",
//                tint = Color.Gray,
//                modifier = Modifier
//                    .size(35.dp))
//        },
//            label = { Text(text = "",
//                color = Color.Gray) }, selected = (selectedIndex.value == 2), onClick = {
//                navController.navigate(ROUTE_SETTINGS) {
//                    popUpTo(ROUTE_FAVOURITE) { inclusive = true }
//                }
//            },
//            selectedContentColor = Color.DarkGray)
//
//
//    }
//}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    PopularEventsScreen(rememberNavController())
}

