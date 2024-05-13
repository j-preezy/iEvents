package net.ezra.ui.categories



import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME

data class Product(
    var id: String = "",
    val imageUrl: String? = "",
    var eventtitle : String? = "",
    var description : String? = "",
    var date : String? = "",
    var starttime : String? = "",
    var endtime : String? = "",
    var location : String? = "",
)





@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllEventsScreen(navController: NavController, products: List<Product>) {
    var isLoading by remember { mutableStateOf(true) }
    var productList by remember { mutableStateOf(emptyList<Product>()) }
    var displayedProductCount by remember { mutableStateOf(5) }
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchProducts { fetchedProducts ->
            productList = fetchedProducts
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "All Events",
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
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.DarkGray,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Gray,
                )
            )
        }
        ,
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
                    if (productList.isEmpty()) {
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
                            items(productList.take(displayedProductCount)) { product ->
                                ProductListItem(product) {
                                    navController.navigate("productDetail/${product.id}")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        // Load More Button
                        if (displayedProductCount < productList.size) {
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff0FB06A)),
                                onClick = { displayedProductCount += 1 },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "Load More", color = Color.LightGray, fontFamily = FontFamily.Serif)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ProductListItem(product: Product, onItemClick: (String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(product.id) }
    ) {

        Column(
            Modifier.fillMaxSize()
        ) {


            Column {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.imageUrl)
                        .crossfade(true)
                        .build(),
                    loading = {
                        androidx.compose.material3.CircularProgressIndicator()
                    },
                    contentDescription = product.eventtitle,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .padding(5.dp)
                        .fillMaxSize()
                        .height(450.dp)

                )

                Column(
                    modifier = Modifier.padding(

                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {

                        product.eventtitle?.let { Text(text = it,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp) }

                        product.starttime?.let { Text(text = it,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp) }

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {

                        product.location?.let { Text(text = it,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.Gray) }



                        product.date?.let { Text(text = it,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.Gray) }

                    }

                }
            }

        }



    }
}





suspend fun fetchProducts(onSuccess: (List<Product>) -> Unit) {
    val firestore = Firebase.firestore
    val snapshot = firestore.collection("Events").get().await()
    val productList = snapshot.documents.mapNotNull { doc ->
        val product = doc.toObject<Product>()
        product?.id = doc.id
        product
    }
    onSuccess(productList)
}

suspend fun fetchProduct(productId: String, onSuccess: (Product?) -> Unit) {
    val firestore = Firebase.firestore
    val docRef = firestore.collection("Events").document(productId)
    val snapshot = docRef.get().await()
    val product = snapshot.toObject<Product>()
    onSuccess(product)
}
