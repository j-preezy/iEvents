package net.ezra.ui.detail


import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.ezra.R
import net.ezra.ui.categories.Product


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(navController: NavController, productId: String) {

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        product = fetchProduct(productId)
    }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // Display the product name if available
                    Text(
                        text = product?.eventtitle ?: "Details",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif

                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            "backIcon",

                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Gray,
                    navigationIconContentColor = Color.Gray)
            )
        },
        content = {

            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.back3), contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )

            }



            LazyColumn (
                Modifier.fillMaxSize()
            ) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    ) {

                        Card () {

                            Column (
                                Modifier.fillMaxWidth()
                            ) {

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                                    horizontalArrangement = Arrangement.Absolute.SpaceBetween

                                ) {

                                    product?.eventtitle?.let { Text(text = it,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold) }

                                    product?.starttime?.let { Text(text = it,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold) }

                                }

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                                ) {

                                    product?.location?.let { Text(text = it,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 20.sp) }



                                    product?.date?.let { Text(text = it,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 20.sp) }

                                }

                            }

                            product?.let {
                                Column(modifier = Modifier.padding(5.dp)) {
                                    Image(
                                        painter = rememberAsyncImagePainter(it.imageUrl),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .size(height = 450.dp, width = 400.dp)
                                    )
                                    Column (
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(start = 15.dp, end = 15.dp, top = 5.dp)
                                    ) {
                                        it.eventtitle?.let { it1 -> Text(text = it1, style = MaterialTheme.typography.h5, fontFamily = FontFamily.Serif) }

                                        Spacer(modifier = Modifier.height(5.dp))

                                        it.description?.let { it1 -> Text(text = it1, style = MaterialTheme.typography.body1, fontFamily = FontFamily.Serif) }


                                    }
                                }
                            }


                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                    Text(
                                        text = "General Admission",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .padding(start = 15.dp, top = 15.dp)
                                    )

                                    Spacer(modifier = Modifier.width(120.dp))

                                    CounterWithIcons()


                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 20.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.Bottom
                                ) {

                                    Button(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 15.dp, end = 15.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = Color.White,
                                            disabledContainerColor = Color.Black,
                                            disabledContentColor = Color.White
                                        )


                                    ) {

                                        Text(
                                            text = "Reserve A Spot",
                                            fontFamily = FontFamily.Serif,
                                            color = Color.White
                                        )

                                    }

                                }


                            }

                        }

                    }

                }

            }
        }
    )
}

@Composable
fun CounterWithIcons() {
    var count by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            if (count > 0) {
            count--
            }
        }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Decrease")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = count.toString(), style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(onClick = { count++ }) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Increase")
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = Color.White) {
        CounterWithIcons()
    }
}



suspend fun fetchProduct(productId: String): Product? {
    val db = FirebaseFirestore.getInstance()
    val productsCollection = db.collection("Events")

    return try {
        val documentSnapshot = productsCollection.document(productId).get().await()
        if (documentSnapshot.exists()) {
            val productData = documentSnapshot.data ?: return null
            Product(
                id = productId,
                eventtitle = productData["eventtitle"] as String, // Assuming the field in Firestore is "eventtitle"
                imageUrl = productData["imageUrl"] as String, // Assuming the field in Firestore is "imageUrl"
                description = productData["description"] as String,
                date = productData["date"] as String,
                starttime = productData["starttime"] as String,
                endtime = productData["endtime"] as String,
                location = productData["location"] as String,

            // Assuming the field in Firestore is "description"
                // Add other product properties here
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}