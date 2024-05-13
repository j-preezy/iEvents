package net.ezra.navigation

import SplashScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.auth.Log_InScreen
import net.ezra.ui.auth.PasswordReset
import net.ezra.ui.auth.Sign_UpScreen
import net.ezra.ui.categories.AllEventsScreen
import net.ezra.ui.categories.ArtEvents
import net.ezra.ui.categories.BusinessEvents
import net.ezra.ui.categories.CharityEvents
import net.ezra.ui.categories.CulturalEvents
import net.ezra.ui.categories.EducationalEvents
import net.ezra.ui.categories.GamesEvents
import net.ezra.ui.categories.MusicEvents
import net.ezra.ui.categories.ReligiousEvents
import net.ezra.ui.detail.EventDetailScreen
import net.ezra.ui.events.CreateEventScreen
import net.ezra.ui.home.HomeScreen
import net.ezra.ui.register.RegisterScreen
import net.ezra.ui.search.Search
import net.ezra.ui.settings.SettingsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH

)

{
    BackHandler {
        navController.popBackStack()

    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

//        composable(ROUTE_SPLASH) {
//            if (isUserAuthenticated()) {
//                navController.navigate(ROUTE_HOME)
//            } else {
//                navController.navigate(ROUTE_LOG_IN)
//            }
//        }



        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_CREATE) {
            CreateEventScreen(navController)
        }



        composable(ROUTE_RESET) {
            PasswordReset(navController)
        }



        composable(ROUTE_SETTINGS) {
            SettingsScreen(navController = navController)
        }

        composable(ROUTE_SEARCH) {
            Search(navController = navController)
        }

        composable(ROUTE_LOGIN) {
            Log_InScreen(navController = navController) {

            }
        }

        composable(ROUTE_SIGNUP) {
            Sign_UpScreen(navController = navController) {

            }
        }

        composable(ROUTE_ALLEVENTS) {
            AllEventsScreen(navController = navController, products = listOf())
        }

        composable(ROUTE_ART) {
            ArtEvents(navController = navController)
        }

        composable(ROUTE_BUSINESS) {
            BusinessEvents(navController = navController)
        }

        composable(ROUTE_CHARITY) {
            CharityEvents(navController = navController)
        }

        composable(ROUTE_CULTURAL) {
            CulturalEvents(navController = navController)
        }

        composable(ROUTE_EDUCATIONAL) {
            EducationalEvents(navController = navController)
        }

        composable(ROUTE_GAMES) {
            GamesEvents(navController = navController)
        }

        composable(ROUTE_MUSIC) {
            MusicEvents(navController = navController)
        }

        composable(ROUTE_RELIGIOUS) {
            ReligiousEvents(navController = navController)
        }


        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }


        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            EventDetailScreen(navController, productId)
        }





        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }






































    }

}









//fun isUserAuthenticated(): Boolean {
//    val firebaseAuth = FirebaseAuth.getInstance()
//    val currentUser = firebaseAuth.currentUser
//    return currentUser != null
//}