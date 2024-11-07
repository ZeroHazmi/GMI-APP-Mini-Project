package com.example.gmiapp

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmiapp.ui.theme.DarkBlue
import com.example.gmiapp.ui.theme.DepartmentData
import com.example.gmiapp.ui.theme.PreUniData
import com.example.gmiapp.ui.theme.TransparentBlue
import ui.BottomNavBar
import ui.BottomNavViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: BottomNavViewModel) {
    var selectedDisplay by remember { mutableStateOf("Diploma") }
    var selectedDepartment by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(bottom = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.gmi_logo),
                                contentDescription = "GMI Logo",
                                modifier = Modifier.size(70.dp)
                            )
                            Text(
                                "Eligibility Checker",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                },
            )
        },
        bottomBar = {
            BottomNavBar(navController, viewModel)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = TransparentBlue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                ProgrammeButton("Diploma", onClick = {
                    selectedDisplay = "Diploma"
                    selectedDepartment = "" // Reset department on display change
                }, isActive = selectedDisplay == "Diploma")

                ProgrammeButton("Pre-Uni", onClick = {
                    selectedDisplay = "Pre-Uni"
                    selectedDepartment = "" // Reset department on display change
                }, isActive = selectedDisplay == "Pre-Uni")
            }
            Spacer(modifier = Modifier.height(10.dp))

            if (selectedDepartment.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = if (selectedDisplay == "Diploma") "Diploma Departments" else "Pre-University Courses",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    when (selectedDisplay) {
                        "Diploma" -> {
                            items(DepartmentData.departments) { department ->
                                ProgramCard(
                                    programmeName = department.name,
                                    numberOfCourses = department.course.size
                                ) {
                                    selectedDepartment = department.name
                                }
                                Spacer(Modifier.height(10.dp))
                            }
                        }

                        "Pre-Uni" -> {
                            items(PreUniData.programmes) { program ->
                                ProgramCard(programmeName = program.name) {
                                    // Handle click or navigate to details
                                }
                                Spacer(Modifier.height(10.dp))
                            }
                        }
                    }
                }
            } else {
                // Display courses for selected department
                Text(
                    text = "Courses in $selectedDepartment",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(DepartmentData.departments.find { it.name == selectedDepartment }?.course ?: emptyList()) { course ->
                        CourseDisplayCard(courseName = course.name) {
                            viewModel.updateCurrentPage("result")
                            navController.navigate("spm")
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
@Composable
fun ProgramCard(programmeName: String, numberOfCourses: Int = 0, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .size(width = 344.dp, height = 100.dp),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            onClick()
        }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = programmeName, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                if (numberOfCourses != 0) {
                    Text(text = "Number of courses: $numberOfCourses", fontSize = 20.sp)
                }
            }
        }
    }
}



@Composable
fun ProgrammeButton(buttonName: String, onClick: () -> Unit, isActive: Boolean) {
    val buttonColor = if (isActive) Color.Gray else Color.White

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.size(width = 160.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        shape = CardDefaults.shape
    ) {
        Text(
            text = buttonName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

@Composable
fun CourseDisplayCard(courseName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(true, onClick = onClick)
            .size(width = 344.dp, height = 100.dp)
            .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            onClick()
        }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = courseName, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                // Description
            }
        }
    }
}