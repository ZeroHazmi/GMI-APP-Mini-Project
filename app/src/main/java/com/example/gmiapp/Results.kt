package com.example.gmiapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmiapp.ui.theme.AllCourse
import com.example.gmiapp.ui.theme.Course
import com.example.gmiapp.ui.theme.CourseData
import com.example.gmiapp.ui.theme.SPMEnquiryRequirements
import com.example.gmiapp.ui.theme.SPMGrade
import com.example.gmiapp.ui.theme.SPMGradesData
import com.example.gmiapp.ui.theme.SPMResult
import com.example.gmiapp.ui.theme.SPMSubject
import com.example.gmiapp.ui.theme.SPMSubjects
import com.example.gmiapp.ui.theme.TransparentBlue
import ui.BottomNavBar
import ui.BottomNavViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddResultPage(navController: NavHostController, viewModel: BottomNavViewModel) {
    var subjects by remember { mutableStateOf(mutableListOf<SPMResult>()) }
    val context = LocalContext.current


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
                                modifier = Modifier.size(100.dp)
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                "Eligibility Checker",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                },
            )
        },
        bottomBar = {
            BottomNavBar(navController, viewModel)

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Calculate qualification
                    val isEligibleDiploma = checkQualificationForDiploma(subjects)
                    if (isEligibleDiploma){
                        showSuccessToastAndRedirect(context, "Qualification successful for all diploma courses!")
                    }else{
                        val isEligibleCRM = checkQualificationForCRM(subjects)
                        if (isEligibleCRM){
                            showSuccessToastAndRedirect(context, "You have only meet the requirements for Diploma in Creative Multimedia")
                        }
                        else{
                            Toast.makeText(context, "You have not meet the requirement", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "Submit", // Button text
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->

        // Display content based on the selected page
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enter Your SPM Results:", style = MaterialTheme.typography.bodyLarge)
                IconButton(onClick = {
                    if (subjects.size <= 12) {
                        subjects = (subjects + SPMResult(
                            SPMSubjects.allSubjects.first(),
                            SPMGradesData.spmGrades.first()
                        )).toMutableList()
                    } else {
                        Log.d("SPMResultPage", "Maximum of 12 subjects reached")
                    }
                }) {
                    Image(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "Add Icon Button",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(subjects.size) { index ->
                        SubjectCardRow (
                            spmSubjects = SPMSubjects.allSubjects,
                            spmGrades = SPMGradesData.spmGrades,
                            selectedSubject = subjects[index].subject,
                            selectedGrade = subjects[index].grade,
                            onSubjectSelected = { selectedSubject ->
                                subjects = subjects.toMutableList().apply {
                                    this[index] = this[index].copy(subject = selectedSubject)
                                }
                            },
                            onGradeSelected = { selectedGrade ->
                                subjects = subjects.toMutableList().apply {
                                    this[index] = this[index].copy(grade = selectedGrade)
                                }
                            },
                            onRemoveSelected = {
                                subjects = subjects.toMutableList().apply {
                                    removeAt(index)
                                }
                            }
                        )
                        Spacer(Modifier.height(15.dp))
                    }
                }

        }
    }
}


@Composable
fun SubjectCardRow(
    spmSubjects: List<SPMSubject>,
    spmGrades: List<SPMGrade>,
    selectedSubject: SPMSubject,
    selectedGrade: SPMGrade,
    onSubjectSelected: (SPMSubject) -> Unit,
    onGradeSelected: (SPMGrade) -> Unit,
    onRemoveSelected: () -> Unit
) {
    var subjectExpanded by remember { mutableStateOf(false) }
    var gradeExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.size(width = 344.dp, height = 100.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dropdown for subject selection
            Column {
                Text(text = "Subject:")
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { subjectExpanded = true }
                        .padding(8.dp)
                ) {

                    Text(text = selectedSubject.name)
                    DropdownMenu(
                        expanded = subjectExpanded,
                        onDismissRequest = { subjectExpanded = false }
                    ) {
                        spmSubjects.forEach { subject ->
                            DropdownMenuItem(onClick = {
                                onSubjectSelected(subject)
                                subjectExpanded = false
                            }, text = {
                                Text(text = subject.name)
                            })
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.width(20.dp))

            // Dropdown for grade selection
            Column {
                Text(text = "Grade:")
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { gradeExpanded = true }
                        .padding(8.dp)
                ) {
                    Text(text = selectedGrade.id)
                    DropdownMenu(
                        expanded = gradeExpanded,
                        onDismissRequest = { gradeExpanded = false }
                    ) {
                        spmGrades.forEach { grade ->
                            DropdownMenuItem(onClick = {
                                onGradeSelected(grade)
                                gradeExpanded = false
                            }, text = {
                                Text(text = grade.id)
                            })
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(20.dp))

            IconButton(
                onClick = onRemoveSelected
            ) {
                Image(
                    painter = painterResource(R.drawable.remove),
                    contentDescription = "Remove Icon Button",
                    modifier = Modifier.size(40.dp)
                )
            }
        }


    }
}

fun checkQualificationForDiploma(subjectResults: List<SPMResult>): Boolean {
    // Define minimum grade requirements
    val requiredGrades = SPMEnquiryRequirements()

    // Collect all subject grades with their names
    val subjectGradesList = mutableListOf<Pair<String, Int>>()

    // Populate the list of subjects and grades
    subjectResults.forEach { result ->
        subjectGradesList.add(Pair(result.subject.name, result.grade.value))
    }

    // Function to find a grade value for a specific subject, fallback to default grade if not found
    fun getGradeForSubject(subjectName: String, defaultGrade: Int): Int {
        return subjectGradesList.find { it.first == subjectName }?.second ?: defaultGrade
    }

    // Check the required subjects meet their grade requirements
    val meetsRequiredGrades = listOf(
        getGradeForSubject("Bahasa Melayu", 0) >= requiredGrades.bahasaMelayu.value,
        getGradeForSubject("Sejarah", 0) >= requiredGrades.sejarah.value,
        getGradeForSubject("Bahasa English", 0) >= requiredGrades.english.value,
        getGradeForSubject("Matematik", 0) >= requiredGrades.mathematics.value
    ).all { it }

    // Check if any subject in the list meets the requirement for science or technical subjects
    val meetsScienceOrTechnicalRequirement = subjectGradesList.any {
        it.first in SPMSubjects.scienceAndTechnicalSubjects.map { subject -> subject.name }
                && it.second >= requiredGrades.scienceOrTechnical.value
    }

    // Ensure there are no more than 12 subjects
    val totalSubjectsValid = subjectGradesList.size <= 12

    // Final check: All the required grades must be met, the subject count must be valid,
    // and at least one science or technical subject must meet the grade requirement
    return meetsRequiredGrades && meetsScienceOrTechnicalRequirement && totalSubjectsValid
}

fun checkQualificationForCRM(subjectResults: List<SPMResult>): Boolean {
    // Define minimum grade requirements
    val bahasaMelayuRequiredGrade = 3 // Equivalent to "C"
    val sejarahRequiredGrade = 1 // Equivalent to "E"
    val otherSubjectRequiredGrade = 3 // Equivalent to "C"

    // Collect all subject grades with their names
    val subjectGradesList = mutableListOf<Pair<String, Int>>()

    // Populate the list of subjects and grades
    subjectResults.forEach { result ->
        subjectGradesList.add(Pair(result.subject.name, result.grade.value))
    }

    // Function to find a grade value for a specific subject, fallback to a failing grade if not found
    fun getGradeForSubject(subjectName: String, defaultGrade: Int): Int {
        return subjectGradesList.find { it.first == subjectName }?.second ?: defaultGrade
    }

    // Check Bahasa Melayu requirement
    val bahasaMelayuMeetsRequirement = getGradeForSubject("Bahasa Melayu", 0) >= bahasaMelayuRequiredGrade

    // Check Sejarah requirement
    val sejarahMeetsRequirement = getGradeForSubject("Sejarah", 0) >= sejarahRequiredGrade

    // Check if at least three other subjects meet the "C" grade requirement
    val meetsOtherSubjectRequirement = subjectGradesList.count { it.second >= otherSubjectRequiredGrade } >= 3

    // Final check: Bahasa Melayu and Sejarah must meet their requirements, and at least three other subjects must have at least "C"
    return bahasaMelayuMeetsRequirement && sejarahMeetsRequirement && meetsOtherSubjectRequirement
}



fun getCourseNameById(courseId: Int): String {
    // Use the courseId to select the correct course list and get the course name
    return when {
        courseId in 0 until CourseData.electricalCourses.size -> CourseData.electricalCourses[courseId].name
        courseId in 0 until CourseData.mechanicalCourses.size -> CourseData.mechanicalCourses[courseId].name
        courseId in 0 until CourseData.computerInformationCourses.size -> CourseData.computerInformationCourses[courseId].name
        courseId == 100 -> CourseData.gappCourse.name  // Example for program-based courses
        courseId == 101 -> CourseData.gufpCourse.name // Example for program-based courses
        else -> "Unknown Course"
    }
}

// Function to show toast and redirect user
fun showSuccessToastAndRedirect(context: Context, text: String) {
    // Show success toast message
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()

    // Redirect to the login page after showing the toast
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login")))
}