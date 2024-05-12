package net.ezra.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_RESET

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordReset(navController: NavHostController) {

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var error by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFF2A2A2A))
            .clip(RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            IconButton(onClick = {
                navController.navigate(ROUTE_LOGIN) {
                    popUpTo(ROUTE_RESET) { inclusive = true }
                }
            }) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Column(
            Modifier.padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Type in your email to receive",
                color = Color.LightGray,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "a password reset link.",
                color = Color.LightGray,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
        }

        Spacer(modifier = Modifier.height(140.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                label = { Text("Type in Email", fontFamily = FontFamily.Serif) },
                onValueChange = { email = it },
                textStyle = TextStyle(Color.White, fontFamily = FontFamily.Serif),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF69DBFF),
                    unfocusedLabelColor = Color.LightGray,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color(0xFF69DBFF)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.text.isNotBlank()) {
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email.text)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Show dialog to inform the user
                                    showDialog = true
                                } else {
                                    // Handle error
                                    error = task.exception?.message ?: "Password reset failed"
                                }
                            }
                    } else {
                        error = "Please enter your email"
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp, 30.dp, 0.dp)
                    .shadow(20.dp, RoundedCornerShape(10.dp))
                    .size(40.dp)
            ) {
                Text(
                    text = "Send Request",
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    // Dialog to inform the user
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Email Sent", fontFamily = FontFamily.Serif, color = Color.White) },
            text = {
                Text(
                    "An email with instructions to reset your password has been sent to your email address.",
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            },
            containerColor = Color.DarkGray,
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_RESET) { inclusive = true }
                        }
                    },
                    content = {
                        Text("OK", fontFamily = FontFamily.Serif)
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),


                )
            },
            modifier = Modifier.background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
        )
    }
}



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    PasswordReset(rememberNavController())
}
