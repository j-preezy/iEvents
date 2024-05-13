package net.ezra.ui.home

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ALLEVENTS
import net.ezra.navigation.ROUTE_ART
import net.ezra.navigation.ROUTE_BUSINESS
import net.ezra.navigation.ROUTE_CHARITY
import net.ezra.navigation.ROUTE_CREATE
import net.ezra.navigation.ROUTE_CULTURAL
import net.ezra.navigation.ROUTE_EDUCATIONAL
import net.ezra.navigation.ROUTE_GAMES
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_MUSIC
import net.ezra.navigation.ROUTE_RELIGIOUS
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_SETTINGS


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {


    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Text(text = "iEvents",
                            fontFamily = FontFamily.Serif,
                            fontSize = 35.sp)

                    }
                },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Gray,

                ),



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
                modifier = Modifier
                    .fillMaxSize()
            ) {

                item {

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))

                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.height(50.dp))

                            Text(text = "Plan Your Best Event",
                                color = Color.White,
                                fontSize = 40.sp,
                                fontFamily = FontFamily.Serif)

                            Text(text = "Explore the Best Events Around You.",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif)

                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            Spacer(modifier = Modifier.height(20.dp))


                            LazyRow (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp, end = 20.dp)
                            ) {



                                item {


                                    Text(text = "Art",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_ART)
                                            }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Business",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_BUSINESS)
                                            }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Charity",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_CHARITY)
                                            }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Cultural",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_CULTURAL)
                                            }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Educational",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_EDUCATIONAL)
                                            }
                                    )



                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Games",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_GAMES)
                                            }
                                    )


                                    Spacer(modifier = Modifier.width(20.dp))

                                    Text(text = "Music",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_MUSIC)
                                            }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    
                                    

                                    Text(text = "Religious",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,

                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ROUTE_RELIGIOUS)
                                            }
                                    )
                                    
                                }

                            }



                        }


                        Spacer(modifier = Modifier.height(120.dp))

                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Row (
                                Modifier.fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Click the button below to view all events",
                                    color = Color.LightGray,
                                    fontSize = 30.sp,
                                    fontFamily = FontFamily.Serif,
                                    textAlign = TextAlign.Center)
                            }
                            

                        }
                        
                        Spacer(modifier = Modifier.height(25.dp))

                        Column (
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Button(
                                onClick = { navController.navigate(ROUTE_ALLEVENTS) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF332F2F),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .size(150.dp)
                            ) {

                                Text(text = "All Events",
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.Serif,
                                    textAlign = TextAlign.Center)

                            }

                        }





                    }



                }




            }





        },


        bottomBar = { BottomBar(navController) }
    )




}



@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(bottom = 5.dp, start = 5.dp, end = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp))
            .border(2.5.dp, Color.Gray, RoundedCornerShape(30.dp))
    ) {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home), "", modifier = Modifier.size(25.dp)) },
            selected = navController.currentDestination?.route == ROUTE_HOME,
            onClick = { navController.navigate(ROUTE_HOME) },
            selectedContentColor = Color.DarkGray
        )

        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.search), "", modifier = Modifier.size(30.dp)) },
            selected = navController.currentDestination?.route == ROUTE_SEARCH,
            onClick = { navController.navigate(ROUTE_SEARCH) },
            selectedContentColor = Color.DarkGray
        )

        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.calendar), "", modifier = Modifier.size(25.dp)) },
            selected = navController.currentDestination?.route == ROUTE_CREATE,
            onClick = { navController.navigate(ROUTE_CREATE) },
            selectedContentColor = Color.DarkGray
        )

        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.settings), "", modifier = Modifier.size(25.dp)) },
            selected = navController.currentDestination?.route == ROUTE_SETTINGS,
            onClick = { navController.navigate(ROUTE_SETTINGS) },
            selectedContentColor = Color.DarkGray
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    HomeScreen(rememberNavController())
}

