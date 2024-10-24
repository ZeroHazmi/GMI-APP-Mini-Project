package com.example.gmiapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmiapp.ui.theme.GMIAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GMIAPPTheme {
                val navController = rememberNavController()
                NavHostContainer(navController = navController)
            }
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppRoute.Splash.route) {
        composable(AppRoute.Splash.route) {
            SplashScreen(navController = navController, onSplashEnded = {
                navController.navigate(AppRoute.HomePage.route)
            })
        }
        composable(AppRoute.HomePage.route) {
            Homepage(navController)
        }
        composable(AppRoute.Courses.route) {
            CoursesScreen()
        }
        composable(AppRoute.Enquiry.route) {
            EnquiryScreen()
        }

    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GMIAPPTheme {

    }
}