package aji.dev.bniassessment.presentation.navigation

import aji.dev.bniassessment.domain.model.Data
import aji.dev.bniassessment.presentation.promo.PromoDetailScreen
import aji.dev.bniassessment.presentation.promo.PromoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavController(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.Detail.route,
        ) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Data>("data")
            if (result != null) PromoDetailScreen(data = result, navController = navController)
        }
        composable(
            route = Screen.Promo.route,
        ) {
            PromoScreen(navController = navController)
        }
    }
}