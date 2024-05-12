package net.ezra.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_SIGNUP


@Composable
fun RegisterScreen(navController: NavHostController) {

    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(painter = painterResource(id = R.drawable.background4), contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxSize()

        ){


            Text(text = "Discover Amazing Events In Your City",
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Light,
                fontSize = 42.sp,

                modifier = Modifier
                    .padding(15.dp))

            Text(text = "The best events planned just for you.",
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,

                modifier = Modifier
                    .padding(15.dp, 0.dp, 0.dp, 0.dp))



        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            modifier = Modifier
                .fillMaxSize()
        ) {


            Text(
                modifier = Modifier

                    .clickable {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_REGISTER) { inclusive = true }
                        }
                    },
                text = "Home",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inversePrimary
            )


            Button(onClick = {
                navController.navigate(ROUTE_SIGNUP) {
                    popUpTo(ROUTE_REGISTER) { inclusive = true }
                }
            },
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.White,
                ),

                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 20.dp
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
//                    .(40.dp, RoundedCornerShape(10.dp))
                    .size(50.dp)



            ) {

                Text(text = "Get Started",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.width(10.dp))

                Icon(Icons.Outlined.ArrowForward, contentDescription = "",
                    tint = Color.Black)



            }

            Row (
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
            ) {

                Text(text = "Already have an account?",
                    color = Color.White,
                    fontFamily = FontFamily.Serif)

                Spacer(modifier = Modifier.width(4.dp))


                Text(text = "Log in",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    textDecoration = TextDecoration.Underline,

                    modifier = Modifier
                        .clickable {
                            navController.navigate(ROUTE_LOGIN) {
                                popUpTo(ROUTE_REGISTER) { inclusive = true }
                            }
                        })

            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    RegisterScreen(rememberNavController())
}

