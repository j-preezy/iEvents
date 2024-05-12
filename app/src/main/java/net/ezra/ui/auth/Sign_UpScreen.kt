package net.ezra.ui.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_SIGNUP


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sign_UpScreen(navController: NavController, onSignUpSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFF2A2A2A))
            .clip(RoundedCornerShape(20.dp)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.Start,
        ) {

            IconButton(onClick = {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_SIGNUP) { inclusive = true }
                }
            }) {
                Icon(
                    Icons.Default.Close, contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "Welcome To iEvents",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            fontSize = 33.sp,
            textAlign = TextAlign.Center,

            modifier = Modifier
        )

        Column(

            modifier = Modifier
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = "Enter your Email",
                        fontFamily = FontFamily.Serif
                    )
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
            emailError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif),
                    modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            var passwordVisible by remember { mutableStateOf(false) }
            var passwordVisible1 by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    error = null },
                label = {
                    Text(
                        text = "Create your Password",
                        fontFamily = FontFamily.Serif
                    )
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
                        modifier = Modifier.clickable {
                            passwordVisible = !passwordVisible
                        }
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF69DBFF),
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF69DBFF),
                    unfocusedTextColor = Color.White
                )

            )
            passwordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif),
                    modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    error = null
                                },
                label = {
                    Text(
                        "Confirm Password",
                        fontFamily = FontFamily.Serif
                    )
                },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    autoCorrect = false,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisible1) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    Image(
                        painter = if (passwordVisible1) {
                            painterResource(R.drawable.view) // Replace with your image resource
                        } else {
                            painterResource(R.drawable.hidden) // Replace with your image resource
                        },
                        contentDescription = if (passwordVisible1) {
                            "Hide password"
                        } else {
                            "Show password"
                        },
                        modifier = Modifier.clickable {
                            passwordVisible1 = !passwordVisible1
                        }
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF69DBFF),
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF69DBFF),
                    unfocusedTextColor = Color.White
                )
            )
            confirmPasswordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif),
                    modifier = Modifier.padding(start = 16.dp, top = 5.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = Color.White)
            } else {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        if (email.isBlank()) {
                            emailError = "Email is required"
                        } else if (password.isBlank()) {
                            passwordError = "Password is required"
                        } else if (confirmPassword.isBlank()) {
                            confirmPasswordError = "Password Confirmation required"
                        } else if (password != confirmPassword) {
                            confirmPasswordError = "Passwords do not match"
                        } else {
                            isLoading = true
                            signUp(email, password, {
                                isLoading = false
                                Toast.makeText(context, "Sign-up successful!", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(ROUTE_HOME)
                                onSignUpSuccess()
                            }) { errorMessage ->
                                isLoading = false
                                confirmPasswordError = errorMessage
                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 0.dp, 40.dp, 0.dp)
                        .shadow(20.dp, RoundedCornerShape(10.dp))
                        .size(40.dp,)
                ) {
                    Text(text = "SIGN UP",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold)
                }



            }
        }
    }
}

private fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods ?: emptyList()
                if (signInMethods.isNotEmpty()) {
                    onError("Email is already registered")
                } else {
                    // Email is not registered, proceed with sign-up
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { signUpTask ->
                            if (signUpTask.isSuccessful) {
                                onSuccess()
                            } else {
                                // Handle specific error cases
                                val errorMessage = when (val exception = signUpTask.exception) {
                                    is FirebaseAuthWeakPasswordException -> "Weak password"
                                    is FirebaseAuthInvalidCredentialsException -> "Invalid email"
                                    is FirebaseAuthUserCollisionException -> "Email is already in use"
                                    else -> exception?.message ?: "Sign-up failed"
                                }
                                onError(errorMessage)
                            }
                        }
                }
            } else {
                onError(task.exception?.message ?: "Failed to check email availability")
            }
        }
}






//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun HomeScreenPreviewLight() {
//    Sign_UpScreen(rememberNavController())
//}



