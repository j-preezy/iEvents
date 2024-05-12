package net.ezra.ui.detail
//
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarColors
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.navigation.NavHostController
//import coil.compose.SubcomposeAsyncImage
//import coil.request.ImageRequest
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
//import net.ezra.R
//import net.ezra.navigation.ROUTE_DETAIL
//import net.ezra.navigation.ROUTE_HOME
//import net.ezra.ui.eventdetail.FirestoreViewModel
//import net.ezra.ui.eventdetail.ItemList
//
//
//data class Item(
//
//    val imageUrl: String? = "",
//    var eventtitle : String? = "",
//    var description : String? = "",
//    var date : String? = "",
//    var starttime : String? = "",
//    var endtime : String? = "",
//    var location : String? = "",
//
//    )
//
//
//class FirestoreViewModel : ViewModel() {
//
//    private val firestore = Firebase.firestore
//    private val itemsCollection = firestore.collection("Events")
//
//    private val _items = MutableLiveData<List<Item>>()
//    val items: LiveData<List<Item>> = _items
//
//    init {
//        fetchItems()
//    }
//
//    fun fetchItems() {
//        itemsCollection.addSnapshotListener { snapshot, error ->
//            if (error != null) {
//                Log.e("FirestoreViewModel", "Error fetching items", error)
//                return@addSnapshotListener
//            }
//
//            val itemList = mutableListOf<Item>()
//            snapshot?.documents?.forEach { document ->
//                val item = document.toObject(Item::class.java)?.copy(eventtitle = document.id)
//                item?.let {
//                    itemList.add(it)
//                }
//            }
//            _items.value = itemList
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ItemList(items: List<net.ezra.ui.eventdetail.Item>) {
//
//    Box (
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//
//        Image(
//            painter = painterResource(id = R.drawable.back3), contentDescription = "",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier
//                .fillMaxSize()
//        )
//
//    }
//
//
//
//    LazyColumn (
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//
//
//        items.forEach { item ->
//            item {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(20.dp)
//                ) {
//
//                    Spacer(modifier = Modifier.height(60.dp))
//
//                    Card() {
//
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//
//                        ) {
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
//                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
//                            ) {
//
//                                item.eventtitle?.let { Text(text = it) }
//
//
//                                item.starttime?.let { Text(text = it) }
//
//                            }
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
//                                horizontalArrangement = Arrangement.End
//                            ) {
//
//                                item.date?.let { Text(text = it) }
//
//                            }
//
//                        }
//
//                        Column {
//                            SubcomposeAsyncImage(
//                                model = ImageRequest.Builder(LocalContext.current)
//                                    .data(item.imageUrl)
//                                    .crossfade(true)
//                                    .build(),
//                                loading = {
//                                    CircularProgressIndicator()
//                                },
//                                contentDescription = item.eventtitle,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .clip(RoundedCornerShape(10.dp))
//
//                            )
//
//                            Column(
//                                modifier = Modifier.padding(
//                                    vertical = 10.dp,
//                                    horizontal = 15.dp
//                                )
//                            ) {
//
//                                item.eventtitle?.let { Text(text = it) }
//
//                                Spacer(modifier = Modifier.height(10.dp))
//
//                                item.description?.let { Text(text = it) }
//
//                            }
//                        }
//
//                    }
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 10.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Bottom
//                    ) {
//
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                        ) {
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            ) {
//
//                                Text(
//                                    text = "General Admission",
//                                    fontWeight = FontWeight.Bold,
//                                    fontFamily = FontFamily.Serif,
//                                    modifier = Modifier
//                                        .padding(start = 20.dp, top = 15.dp)
//                                )
//
//                                Spacer(modifier = Modifier.width(100.dp))
//
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "")
//                                }
//
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = "")
//                                }
//
//
//                            }
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(bottom = 20.dp),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.Bottom
//                            ) {
//
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(start = 20.dp, end = 20.dp),
//                                    colors = ButtonDefaults.buttonColors(
//                                        containerColor = Color.Black,
//                                        contentColor = Color.White,
//                                        disabledContainerColor = Color.Black,
//                                        disabledContentColor = Color.White
//                                    )
//
//
//                                ) {
//
//                                    Text(
//                                        text = "Reserve A Spot",
//                                        fontFamily = FontFamily.Serif
//                                    )
//
//                                }
//
//                            }
//
//
//                        }
//
//                    }
//
//
//                }
//
//            }
//        }
//
//    }
//
//}
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen(navController: NavHostController, viewModel: FirestoreViewModel) {
//
//    val items by viewModel.items.observeAsState(initial = emptyList())
//
//    // Fetch items when the composable is first created
//    LaunchedEffect(viewModel, key2 = true) {
//        viewModel.fetchItems()
//    }
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier
//                            .fillMaxSize()
//                    ) {
//
//                        Text(text = "Detail Event",
//                            fontFamily = FontFamily.Serif)
//
//                    }
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_HOME){
//                            popUpTo(ROUTE_DETAIL) { inclusive = true }
//                        }
//                    }) {
//                        Icon(
//                            Icons.Filled.KeyboardArrowLeft, "",
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Filled.MoreVert, contentDescription = "" )
//                    }
//                },
//
//                colors = TopAppBarColors(
//                    containerColor = Color.Transparent,
//                    scrolledContainerColor = Color.Gray,
//                    navigationIconContentColor = Color.LightGray,
//                    titleContentColor = Color.Gray,
//                    actionIconContentColor = Color.Transparent
//                )
//
//            )
//        },
//        content = {
//
//            Box (
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//
//                Image(
//                    painter = painterResource(id = R.drawable.back3), contentDescription = "",
//                    contentScale = ContentScale.FillBounds,
//                    modifier = Modifier
//                        .fillMaxSize()
//                )
//
//            }
//
//            ItemList(items)
//
//        },
//
//
//
//
//        )
//}
//
//
//
//
//
////@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
////@Composable
////fun HomeScreenPreviewLight() {
////    DetailScreen(rememberNavController())
////}
//
