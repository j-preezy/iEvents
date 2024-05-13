package net.ezra.ui.settings

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.ezra.R
import net.ezra.navigation.ROUTE_LOGIN


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {

    var showLogoutConfirmationDialog by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Text(
                            text = "Settings",
                            fontFamily = FontFamily.Serif
                        )

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "",
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "",
                        )
                    }
                },


                colors = TopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.Gray,
                    navigationIconContentColor = Color.DarkGray,
                    titleContentColor = Color.DarkGray,
                    actionIconContentColor = Color.Transparent
                ),


            )
        },

        content = {

            Box(
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {

                    Spacer(modifier = Modifier.height(55.dp))


                    Spacer(modifier = Modifier.height(35.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,

                        ) {

                        Text(
                            text = "Help",
                            color = Color.Gray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Left,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )

                        Column() {

                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {

                                Icon(
                                    painter = painterResource(id = R.drawable.text),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(25.dp),
                                    tint = Color.Gray
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(text = "Text Support",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { })

                            }



                            Spacer(modifier = Modifier.height(10.dp))

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Gray)
                                    .padding(0.2.dp)
                            ) {

                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(35.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,

                        ) {

                        Text(
                            text = "Share",
                            color = Color.Gray,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )

                        Column() {

                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {


                                Icon(
                                    imageVector = Icons.Default.Share, contentDescription = "",
                                    modifier = Modifier
                                        .size(25.dp),
                                    tint = Color.Gray
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(text = "Invite Friends",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { })

                            }

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Gray)
                                    .padding(0.2.dp)
                            ) {

                            }


                        }


                    }

                    Spacer(modifier = Modifier.height(35.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,

                        ) {

                        Text(
                            text = "Account",
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,

                            ) {


                        }



                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { showLogoutConfirmationDialog = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "",
                                    modifier = Modifier.size(25.dp),
                                    tint = Color.Gray
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = "Log Out",
                                    color = Color.LightGray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Serif
                                )
                            }

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Gray)
                                    .padding(0.2.dp)
                            ) {

                            }

                            // Logout confirmation dialog
                            if (showLogoutConfirmationDialog) {
                                AlertDialog(
                                    onDismissRequest = {
                                        // Dismiss the dialog if the user clicks outside of it
                                        showLogoutConfirmationDialog = false
                                    },
                                    title = {
                                        Text("Log Out", fontFamily = FontFamily.Serif, color = Color.White)
                                    },
                                    text = {
                                        Text("Are you sure you want to log out?", fontFamily = FontFamily.Serif, color = Color.White)
                                    },
                                    containerColor = Color.DarkGray,
                                    confirmButton = {
                                        Button(
                                            onClick = {
//                                                // Perform logout action and navigate to login screen
//                                                logoutAndNavigateToLogin(navController)
                                                showLogoutConfirmationDialog = false
                                                FirebaseAuth.getInstance().signOut()
                                                // Navigate to the login screen
                                                navController.navigate(ROUTE_LOGIN)
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White,
                                                contentColor = Color.Black
                                            ),
                                        ) {
                                            Text("Confirm", fontFamily = FontFamily.Serif)
                                        }


                                    },

                                    dismissButton = {
                                        Button(
                                            onClick = {
                                                // Dismiss the dialog
                                                showLogoutConfirmationDialog = false
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White,
                                                contentColor = Color.Black
                                            ),
                                        ) {
                                            Text("Cancel", fontFamily = FontFamily.Serif)
                                        }
                                    }
                                )
                            }
                        }


                    }





                }

            }


        },



    )
}

// Logic to logout and navigate back to login screen
fun logoutAndNavigateToLogin(navController: NavHostController) {
    // Perform logout action here
    // For example, clear user session data or perform any other necessary cleanup
    // After logout, navigate to the login screen
    navController.navigate(ROUTE_LOGIN) {
        // Pop up to the login screen, removing all other destinations from the back stack
        popUpTo("login") {
            // Exclude the login destination from the back stack
            inclusive = false
        }
        // Ensure the login screen is always treated as a top-level destination
        launchSingleTop = true
    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    SettingsScreen(rememberNavController())
}

