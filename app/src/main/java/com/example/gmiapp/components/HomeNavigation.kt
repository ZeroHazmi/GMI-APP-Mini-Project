package com.example.gmiapp.components

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.gmiapp.AddResultPage
import com.example.gmiapp.EnquiryScreen
import com.example.gmiapp.HomeScreen
import ui.BottomNavViewModel

fun NavGraphBuilder.homeDestination(navController: NavHostController, viewModel: BottomNavViewModel) {
    composable(AppRoute.Home.route) {
        HomeScreen(navController, viewModel)
    }
}

fun NavGraphBuilder.addResultDestination(navController: NavHostController, viewModel: BottomNavViewModel) {
    composable(AppRoute.Result.route) {
        AddResultPage(navController, viewModel)
    }
}

fun NavGraphBuilder.enquiryDestination(navController: NavHostController, viewModel: BottomNavViewModel) {
    composable(AppRoute.Enquiry.route) {
        EnquiryScreen(navController, viewModel)
    }
}

sealed class AppRoute(val route: String) {
    object Splash : AppRoute("splash")
    object Home : AppRoute("homepage")
    object Enquiry : AppRoute("enquiry")
    object Result : AppRoute("spm")
}