package net.ezra.ui.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharityEvents(navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    var charityList by remember { mutableStateOf(emptyList<Product>()) }
    var displayedCharityCount by remember { mutableStateOf(5) }
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchCharity { fetchedCharity ->
            charityList = fetchedCharity
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Charity",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME)
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            "backIcon",
                            Modifier.size(40.dp)
                        )
                    }
                },

                colors = centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.DarkGray,
                    navigationIconContentColor = Color.Gray,
                    titleContentColor = Color.Gray,
                    actionIconContentColor = Color.Transparent)

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



            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {




                if (isLoading) {
                    // Progress indicator
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(progress = progress / 100f)
                        Text(text = "Loading... $progress%", fontSize = 30.sp, fontFamily = FontFamily.Serif, color = Color.White)
                    }
                } else {
                    if (charityList.isEmpty()) {
                        // No products found
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(text = "No Events Found",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center,
                                color = Color.LightGray)

                        }
                    } else {
                        // Products list
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1),
                            contentPadding = PaddingValues(5.dp, 5.dp)
                        ) {
                            items(charityList.take(displayedCharityCount)) { product ->
                                ProductListItem(product) {
                                    navController.navigate("productDetail/${product.id}")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        // Load More Button
                        if (displayedCharityCount < charityList.size) {
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff0FB06A)),
                                onClick = { displayedCharityCount += 1 },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "Load More")
                            }
                        }
                    }
                }
            }
        }
    )
}


suspend fun fetchCharity(onSuccess: (List<Product>) -> Unit) {
    val firestore = Firebase.firestore
    val snapshot = firestore.collection("Events")
        .whereEqualTo("category", "Charity") // Fetch events with category "Music"
        .get().await()
    val charityList = snapshot.documents.mapNotNull { doc ->
        val charity = doc.toObject<Product>()
        charity?.id = doc.id
        charity
    }
    onSuccess(charityList)
}