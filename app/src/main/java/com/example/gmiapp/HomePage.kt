package com.example.gmiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Homepage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.gmi_logo),
            contentDescription = "GMI Logo",
            modifier = Modifier.size(50.dp)
        )

        Text("Homepage", style = MaterialTheme.typography.headlineMedium)
        Text("subtitle", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(24.dp))



        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* TODO: Navigate to List of Courses */ }) {
            Text("List of Courses")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO: Navigate to Enquiry */ }) {
            Text("Enquiry")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProgrammeCard(programmeText: String) {
    Card(){

    }
}

@Composable
fun CourseLazyColumn() {

}

@Preview
@Composable
fun HomepagePreview(){
    val navController = rememberNavController()
    Homepage(navController = navController)
}
