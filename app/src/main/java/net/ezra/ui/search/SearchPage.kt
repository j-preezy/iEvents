package net.ezra.ui.search


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_SEARCH


data class YourDataClass(

    val id: String? = "",

    val imageUrl: String? = "",
    val starttime: String? = "",
    val eventtitle: String? = "",
    val date: String? = "",
    val location: String? = ""

)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavHostController) {
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var filteredData by remember { mutableStateOf(emptyList<YourDataClass>()) }

    // Firestore reference
    val firestore = Firebase.firestore

    DisposableEffect(searchText.text) {
        val query = firestore.collection("Events")
            .whereGreaterThanOrEqualTo("eventtitle", searchText.text)
            .whereLessThanOrEqualTo("eventtitle", searchText.text + "\uf8ff")


        val listener = query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                // Handle error
                return@addSnapshotListener
            }

            snapshot?.let {
                val data = it.toObjects(YourDataClass::class.java)
                filteredData = data
            }
        }

        onDispose {
            listener.remove()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Search Events",
                        fontFamily = FontFamily.Serif)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_SEARCH) { inclusive = true }
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            "backIcon",

                        )
                    }
                },



                colors = topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
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

            Column(

                modifier = Modifier
                    .fillMaxSize()

            ) {



                Spacer(modifier = Modifier.height(70.dp))

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search by name..") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = ""
                        ) },
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )

                    )

                Spacer(modifier = Modifier.height(5.dp))


                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    Modifier.padding(5.dp)) {

                    items(filteredData) { item ->

                        Card(
                            Modifier.padding(3.dp)
                        ) {

                            Column (
                                modifier = Modifier
//                                    .padding(it)
                                    .fillMaxSize()
                                    .padding(5.dp)
                                ,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ){

                                Column(
                                    Modifier.fillMaxWidth()
                                ) {
                                    SubcomposeAsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.imageUrl)
                                            .crossfade(true)
                                            .build(),
                                        loading = {
                                            CircularProgressIndicator()
                                        },
                                        contentDescription = item.eventtitle,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .height(250.dp)
                                            .width(300.dp),
                                        contentScale = ContentScale.FillBounds

                                    )
                                }

                                Column (
                                    Modifier.fillMaxWidth()
                                ) {

                                    Row (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 5.dp, top = 5.dp),
                                    ) {

                                        item.eventtitle?.let { Text(text = it,
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight.W700,
                                            fontSize = 15.sp) }

                                    }

                                    Row (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 5.dp, top = 5.dp),
                                    ) {

                                        item.date?.let { Text(text = it,
                                            fontFamily = FontFamily.Serif,
                                            fontWeight = FontWeight.W500,
                                            fontSize = 15.sp) }

                                    }

                                }




                            }

                        }

                    }

                }

            }
        },

        bottomBar = { net.ezra.ui.events.BottomBar(navController) }


        )



}





