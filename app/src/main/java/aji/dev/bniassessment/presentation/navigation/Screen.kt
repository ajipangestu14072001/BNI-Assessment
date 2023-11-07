package aji.dev.bniassessment.presentation.navigation

sealed class Screen (val route: String){
    data object Detail : Screen(route = "detail")
    data object Promo : Screen(route = "promo")
    data object Portofolio : Screen(route = "portofolio")
    data object Qris : Screen(route = "qris")
}