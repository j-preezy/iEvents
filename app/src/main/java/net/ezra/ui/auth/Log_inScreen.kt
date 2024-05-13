package net.ezra.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_RESET

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Log_InScreen(navController: NavHostController, onLoginSuccess: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    BackHandler {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFF2A2A2A))
            .clip(RoundedCornerShape(30.dp)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            IconButton(onClick = {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_LOGIN) { inclusive = true }
                }
            }) {
                Icon(
                    Icons.Default.Close, contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Text(
            text = "Welcome Back",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(50.dp))

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mylogo1),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))


            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = email,
                label = {
                    Text(
                        text = "Email",
                        fontFamily = FontFamily.Serif
                    )
                },
                onValueChange = {
                    email = it
                    error = null
                },
                textStyle = TextStyle(Color.White, fontFamily = FontFamily.Serif),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF69DBFF),
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF69DBFF)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Inside the Column for password field
            var passwordVisible by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = password,
                label = {
                    Text(
                        text = "Password",
                        fontFamily = FontFamily.Serif
                    )
                },
                onValueChange = {
                    password = it
                    error = null
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    Image(
                        painter = if (passwordVisible) {
                            painterResource(R.drawable.view) // Replace with your image resource
                        } else {
                            painterResource(R.drawable.hidden) // Replace with your image resource
                        },
                        contentDescription = if (passwordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        },
                        modifier = Modifier
                            .clickable {
                                passwordVisible = !passwordVisible
                            }
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                },
                textStyle = TextStyle(Color.White, fontFamily = FontFamily.Serif),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    autoCorrect = false,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF69DBFF),
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF69DBFF)
                )
            )


            error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp), color = Color.White)
            } else {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            error = "Please fill in all fields"
                        } else {
                            isLoading = true
                            FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        navController.navigate(ROUTE_HOME)
                                    } else {
                                        val exception = task.exception
                                        if (exception != null) {
                                            when (exception) {
                                                is FirebaseAuthInvalidUserException -> {
                                                    error = "Invalid email address"
                                                }
                                                is FirebaseAuthInvalidCredentialsException -> {
                                                    error = "Incorrect password"
                                                }
                                                else -> {
                                                    error = exception.message ?: "Login failed"
                                                }
                                            }
                                        } else {
                                            error = "Login failed"
                                        }
                                    }
                                }
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 0.dp, 30.dp, 0.dp)
                        .shadow(20.dp, RoundedCornerShape(10.dp))
                        .size(40.dp,)
                ) {
                    Text(
                        text = "LOG IN",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val forgot by remember { mutableStateOf(TextFieldValue("")) }
                Text(
                    text = "Forgot Your Password ?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.clickable {
                        navController.navigate(ROUTE_RESET) {
                            popUpTo(ROUTE_LOGIN) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
