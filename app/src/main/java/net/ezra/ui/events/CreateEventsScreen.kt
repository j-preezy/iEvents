package net.ezra.ui.events

//noinspection UsingMaterialAndMaterial3Libraries,UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import net.ezra.R
import net.ezra.navigation.ROUTE_CREATE
import net.ezra.navigation.ROUTE_DETAIL
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_SETTINGS
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CreateEventScreen(navController: NavHostController) {




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

                        Text(text = "Create An Event",
                            fontFamily = FontFamily.Serif)

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME){
                            popUpTo(ROUTE_DETAIL) { inclusive = true }
                        }
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "",
                        )
                    }
                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )

            )
        },

        content = {

            var photoUri: Uri? by remember { mutableStateOf(null) }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                photoUri = uri
            }

            var eventtitle by rememberSaveable {
                mutableStateOf("")
            }

            var description by rememberSaveable {
                mutableStateOf("")
            }
            val context = LocalContext.current

            var date by rememberSaveable {
                mutableStateOf("")
            }

            var starttime by rememberSaveable {
                mutableStateOf("")
            }


            var endtime by rememberSaveable {
                mutableStateOf("")
            }

            var location by rememberSaveable {
                mutableStateOf("")
            }


            var category by rememberSaveable {
                mutableStateOf("")
            }





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

                    Spacer(modifier = Modifier.height(30.dp))

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 80.dp)
                    ) {

                        Card (
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        ) {

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                            ) {

                                Spacer(modifier = Modifier.height(20.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),

                                    ) {

                                    Text(text = "CATEGORY",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)

                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ){

                                    val categories = listOf("Art", "Music", "Games", "Cultural", "Educational", "Charity", "Religious", "Business")
                                    var selectedCategory by remember { mutableStateOf<String?>(null) }

                                    DropdownTextField(
                                        options = categories,
                                        onItemSelected = { category = it },
                                        initialSelectedItem = category,

                                    )

                                }



                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {

                                Row {
                                    Text(text = "EVENT TITLE",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {



                                    OutlinedTextField(

                                        modifier = Modifier
                                            .fillMaxWidth(),

                                        textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                        value = eventtitle ,
                                        onValueChange = { eventtitle = it },
                                        shape = RoundedCornerShape(20.dp),
                                        placeholder = { Text(text = "Title",
                                            color = Color.Gray,
                                            fontFamily = FontFamily.Serif)
                                        },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Text,
                                            capitalization = KeyboardCapitalization.Sentences,
                                            imeAction = ImeAction.Next,
                                            ),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = Color.DarkGray,
                                            cursorColor = Color.DarkGray
                                        )
                                    )

                                }



                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {

                                Row {
                                    Text(text = "DESCRIPTION",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {


                                    OutlinedTextField(

                                        modifier = Modifier
                                            .fillMaxWidth(),

                                        textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                        value = description ,
                                        onValueChange = { description = it },
                                        shape = RoundedCornerShape(20.dp),
                                        placeholder = { Text(text = "Description",
                                            color = Color.Gray,
                                            fontFamily = FontFamily.Serif) },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Text,
                                            capitalization = KeyboardCapitalization.Sentences,
                                            imeAction = ImeAction.Next,
                                        ),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = Color.DarkGray,
                                            cursorColor = Color.DarkGray
                                        )
                                    )

                                }



                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {

                                Row {
                                    Text(text = "DATE",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)
                                }

                                Spacer(modifier = Modifier.height(10.dp))



                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .weight(1f),
                                        textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                        value = date,
                                        onValueChange = {}, // Empty lambda to prevent changing text programmatically
                                        shape = RoundedCornerShape(20.dp),
                                        placeholder = {
                                            Text(
                                                "Select Date",
                                                color = Color.Gray,
                                                fontFamily = FontFamily.Serif
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next,
                                        ),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = Color.DarkGray,
                                            cursorColor = Color.Transparent // Hides cursor
                                        )
                                    )

                                    IconButton(
                                        onClick = {
                                            // Display dialog with DatePicker
                                            val calendar = Calendar.getInstance()
                                            val datePickerDialog = DatePickerDialog(
                                                context,
                                                { _, year, month, dayOfMonth ->
                                                    // Format the selected date
                                                    val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                                        .format(Calendar.getInstance().apply {
                                                            set(year, month, dayOfMonth)
                                                        }.time)
                                                    date = selectedDate
                                                },
                                                calendar.get(Calendar.YEAR),
                                                calendar.get(Calendar.MONTH),
                                                calendar.get(Calendar.DAY_OF_MONTH)
                                            )

                                            // Set minimum date to current date
                                            datePickerDialog.datePicker.minDate = calendar.timeInMillis
                                            datePickerDialog.show()
                                        }
                                    ) {
                                        Icon(Icons.Filled.DateRange, contentDescription = "Select Date")
                                    }
                                }





                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {

                                Row (

                                ) {
                                    Text(text = "START TIME",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)

                                    Spacer(modifier = Modifier.width(110.dp))

                                    Text(text = "END TIME",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {


                                    Row(

                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .width(120.dp),
                                            textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                            value = starttime,
                                            onValueChange = { newTime ->
                                                if (newTime.length <= 5 && newTime.matches(Regex("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]\$"))) { // Limit to HH:mm format (5 characters)
                                                    starttime = newTime
                                                }
                                            },
                                            shape = RoundedCornerShape(20.dp),
                                            placeholder = {
                                                Text(
                                                    text = "Start Time",
                                                    color = Color.Gray,
                                                    fontFamily = FontFamily.Serif
                                                )
                                            },
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Next,
                                            ),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedBorderColor = Color.DarkGray,
                                                cursorColor = Color.Transparent // Hides cursor
                                            )
                                        )

                                        IconButton(
                                            onClick = {
                                                // Display dialog with TimePicker
                                                val calendar = Calendar.getInstance()
                                                val timePickerDialog = TimePickerDialog(
                                                    context,
                                                    { _, hourOfDay, minute ->
                                                        // Format the selected time
                                                        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                                                        starttime = selectedTime
                                                    },
                                                    calendar.get(Calendar.HOUR_OF_DAY),
                                                    calendar.get(Calendar.MINUTE),
                                                    true // 24-hour format
                                                )

                                                timePickerDialog.show()
                                            }
                                        ) {
                                            Image(painter = painterResource(id = R.drawable.clock), contentDescription = "Select Time", modifier = Modifier.size(18.dp))
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))



                                    Row(

                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .width(120.dp),
                                            textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                            value = endtime,
                                            onValueChange = { newTime ->
                                                if (newTime.length <= 5 && newTime.matches(Regex("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]\$"))) { // Limit to HH:mm format (5 characters)
                                                    endtime = newTime
                                                }
                                            },
                                            shape = RoundedCornerShape(20.dp),
                                            placeholder = {
                                                Text(
                                                    text = "End Time",
                                                    color = Color.Gray,
                                                    fontFamily = FontFamily.Serif
                                                )
                                            },
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Next,
                                            ),
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedBorderColor = Color.DarkGray,
                                                cursorColor = Color.Transparent // Hides cursor
                                            )
                                        )

                                        IconButton(
                                            onClick = {
                                                // Display dialog with TimePicker
                                                val calendar = Calendar.getInstance()
                                                val timePickerDialog = TimePickerDialog(
                                                    context,
                                                    { _, hourOfDay, minute ->
                                                        // Format the selected time
                                                        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                                                        endtime = selectedTime
                                                    },
                                                    calendar.get(Calendar.HOUR_OF_DAY),
                                                    calendar.get(Calendar.MINUTE),
                                                    true // 24-hour format
                                                )

                                                timePickerDialog.show()
                                            }
                                        ) {
                                            Image(painter = painterResource(id = R.drawable.clock), contentDescription = "Select Time", modifier = Modifier.size(18.dp))
                                        }
                                    }


                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Row {
                                    Text(text = "LOCATION",
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif)
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {


                                    OutlinedTextField(

                                        modifier = Modifier
                                            .fillMaxWidth(),

                                        textStyle = TextStyle(fontFamily = FontFamily.Serif),
                                        value = location ,
                                        onValueChange = { location = it },
                                        shape = RoundedCornerShape(20.dp),
                                        placeholder = { Text(text = "Location",
                                            color = Color.Gray,
                                            fontFamily = FontFamily.Serif) },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Text,
                                            capitalization = KeyboardCapitalization.Sentences,
                                            imeAction = ImeAction.Done,
                                        ),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = Color.DarkGray,
                                            cursorColor = Color.DarkGray
                                        )
                                    )

                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Row (
                                    ) {


                                        OutlinedButton(
                                            onClick = {
                                                launcher.launch(
                                                    PickVisualMediaRequest(
                                                        //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                                        //Or use .VideoOnly if you only want videos.
                                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                                    )
                                                )
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                contentColor = Color.DarkGray,
                                                containerColor = Color.LightGray
                                            )
                                        ) {
                                            Text("Add Image")
                                        }

                                        if (photoUri != null) {
                                            //Use Coil to display the selected image
                                            val painter = rememberAsyncImagePainter(
                                                ImageRequest
                                                    .Builder(LocalContext.current)
                                                    .data(data = photoUri)
                                                    .build()
                                            )

                                            Image(
                                                painter = painter,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .size(150.dp)
                                                    .fillMaxWidth()
                                                    .border(1.dp, Color.Gray),
                                                contentScale = ContentScale.Crop,

                                                )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Spacer(modifier = Modifier.width(15.dp))

                                }

                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Row {
                                        OutlinedButton(
                                            onClick = {
                                                photoUri?.let { uri ->
                                                    // Upload image to Firebase Storage
                                                    uploadImagetoFirebaseStorage(uri, eventtitle, description, date, starttime, endtime, location, category = category
                                                    )

                                                    // After successful upload, refresh the page by clearing the fields
                                                    eventtitle = ""
                                                    description = ""
                                                    date = ""
                                                    starttime = ""
                                                    endtime = ""
                                                    location = ""
                                                    category = ""
                                                    // If you're using state variables, make sure to update them to trigger recomposition.
                                                    // For example:
                                                    // eventTitleState = mutableStateOf("")
                                                    // descriptionState = mutableStateOf("")
                                                    // dateState = mutableStateOf("")
                                                    // starttimeState = mutableStateOf("")
                                                    // endtimeState = mutableStateOf("")
                                                    // locationState = mutableStateOf("")
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.Black,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Text(
                                                text = "Create Event",
                                                fontFamily = FontFamily.Serif
                                            )
                                        }

                                    }


                                }


                                Spacer(modifier = Modifier.height(200.dp))

                            }

                        }

                    }

                }



            }


        },

        bottomBar = { BottomBar(navController) }

    )

}



fun formatDateString(dateString: String): String {
    // Remove any non-numeric characters
    val cleanedDate = dateString.replace(Regex("[^\\d]"), "")

    // Format the date string into DD/MM/YYYY format
    val formattedDate = if (cleanedDate.length >= 2 && cleanedDate.length < 4) {
        cleanedDate.substring(0, 2) + "/" + cleanedDate.substring(2)
    } else if (cleanedDate.length >= 4) {
        cleanedDate.substring(0, 2) + "/" + cleanedDate.substring(2, 4) + "/" + cleanedDate.substring(4)
    } else {
        cleanedDate
    }

    return formattedDate
}


fun formatTimeString(timeString: String): String {
    val inputFormat = SimpleDateFormat("HHmm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    return try {
        val date = inputFormat.parse(timeString)
        outputFormat.format(date)
    } catch (e: Exception) {
        timeString // Return the original string if parsing fails
    }
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



@Composable
fun DropdownTextField(
    options: List<String>,
    onItemSelected: (String) -> Unit,
    initialSelectedItem: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf(initialSelectedItem ?: options.firstOrNull()) }

    Column (
        Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = category ?: "",
                onValueChange = { category = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                readOnly = true,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.DarkGray,
                    cursorColor = Color.DarkGray
                )
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Expand")
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    category = option
                    onItemSelected(option)
                    expanded = false
                }) {
                    Text(text = option, fontFamily = FontFamily.Serif)
                }
            }
        }
    }
}

fun uploadImagetoFirebaseStorage(imageUri: Uri, eventtitle: String, description: String, date: String, starttime: String, endtime: String, location: String, category: String) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}")

    val uploadTask = imageRef.putFile(imageUri)
    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            saveToFirestore(downloadUri.toString(), eventtitle, description, date, starttime, endtime, location, category
            )
        } else {


        }
    }
}

fun saveToFirestore(
    imageUrl: String,
    eventtitle: String,
    description: String,
    date: String,
    starttime: String,
    endtime: String,
    location: String,
    category: String // Add category parameter
) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "eventtitle" to eventtitle,
        "description" to description,
        "date" to date,
        "starttime" to starttime,
        "endtime" to endtime,
        "location" to location,
        "category" to category // Include category in the document
    )

    db.collection("Events")
        .add(imageInfo)
        .addOnSuccessListener { documentReference ->
            // Document added successfully
        }
        .addOnFailureListener { e ->
            // Handle error
        }
}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    CreateEventScreen(rememberNavController())
}

