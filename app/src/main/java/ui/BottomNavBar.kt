package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmiapp.R
import com.example.gmiapp.components.AppRoute
import com.example.gmiapp.ui.theme.DarkBlue

@Composable
fun BottomNavBar(navController: NavHostController, viewModel: BottomNavViewModel) {
    val navState by viewModel.uiState.collectAsState()

    BottomAppBar(
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationButton(
                iconResId = R.drawable.home,
                label = "Home",
                isActive = navState.currentPage == "home",
                onClick = {
                    viewModel.updateCurrentPage("home")
                    navController.navigate(route = AppRoute.Home.route)
                }
            )
            NavigationButton(
                iconResId = R.drawable.result,
                label = "Result",
                isActive = navState.currentPage == "result",
                onClick = {
                    viewModel.updateCurrentPage("result")
                    navController.navigate(route = AppRoute.Result.route)
                }
            )
            NavigationButton(
                iconResId = R.drawable.info,
                label = "Enquiry",
                isActive = navState.currentPage == "enquiry",
                onClick = {
                    viewModel.updateCurrentPage("enquiry")
                    navController.navigate(route = AppRoute.Enquiry.route)
                }
            )
        }
    }
}

@Composable
fun NavigationButton(
    iconResId: Int,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    val buttonColour = if (isActive) Color.Black else DarkBlue
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = CardDefaults.shape
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = "$label Button",
                modifier = Modifier.size(40.dp),
                colorFilter = ColorFilter.tint(buttonColour)
            )
            Text(label, fontSize = 16.sp, color = buttonColour)
        }
    }
}