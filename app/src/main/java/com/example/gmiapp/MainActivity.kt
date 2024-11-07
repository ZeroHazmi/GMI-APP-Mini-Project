package com.example.gmiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmiapp.components.AppRoute
import com.example.gmiapp.components.addResultDestination
import com.example.gmiapp.components.enquiryDestination
import com.example.gmiapp.components.homeDestination
import com.example.gmiapp.ui.theme.GMIAPPTheme
import ui.BottomNavViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GMIAPPTheme {
                val navController = rememberNavController()
                val viewModel: BottomNavViewModel = viewModel()
                NavHostContainer(navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, viewModel: BottomNavViewModel) {
    NavHost(navController = navController, startDestination = AppRoute.Home.route){
        homeDestination(navController, viewModel)
        addResultDestination(navController, viewModel)
        enquiryDestination(navController, viewModel)
    }

}