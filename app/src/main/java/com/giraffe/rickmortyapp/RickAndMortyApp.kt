package com.giraffe.rickmortyapp

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.giraffe.rickmortyapp.presentation.home.HomeScreen
import com.giraffe.rickmortyapp.presentation.profile.ProfileScreen
import kotlinx.serialization.Serializable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RickAndMortyApp(
    navController: NavHostController = rememberNavController()

) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        AppNavGraph(navController)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    SharedTransitionLayout{
        NavHost(navController = navController, startDestination = HomeScreenRoute) {
            composable<HomeScreenRoute> { HomeScreen(this,navController) }
            composable<ProfileScreenRoute> {
                val args = it.toRoute<ProfileScreenRoute>()
                ProfileScreen(this, args.id)
            }
        }
    }



}

@Serializable
object HomeScreenRoute

@Serializable
data class ProfileScreenRoute(
    val id: String
)

fun NavController.navigateToProfile(id: String) {
    navigate(ProfileScreenRoute(id))
}



