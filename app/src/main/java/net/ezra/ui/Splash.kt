
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_REGISTER

@Composable
fun SplashScreen(navController: NavHostController) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = androidx.compose.animation.core.tween(
                durationMillis = 800,
                easing = {
                    android.view.animation.OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(3000L)

        // Check if the user is already signed in
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is signed in
            // Navigate to the appropriate screen for authenticated users
            navController.navigate(ROUTE_HOME)
        } else {
            // User is not signed in
            // Navigate to the login screen
            navController.navigate(ROUTE_REGISTER)
        }
    }

    // Image
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAE4D3)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.mylogo1),
            contentDescription = "Logo",
            modifier = Modifier
                .border(0.5.dp, Color.Black, RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds,

        )
    }
}
