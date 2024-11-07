package com.example.gmiapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmiapp.ui.theme.TransparentBlue
import ui.BottomNavBar
import ui.BottomNavViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnquiryScreen(navController: NavHostController, viewModel: BottomNavViewModel) {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    var aboutUs =
        "In 1991, German-Malaysian Institute (GMI), a hub for advanced skills training was established. GMI provides a comprehensive and well-balanced training of practical and theoretical. The institute offers a broad-based engineering education, with opportunities for specialization and self-directed learning and development. \n" +
                "\n" +
                "GMI offers diverse training programmes and services comprising full time diploma programmes, a pre-university programme (A-Level), skills upgrading technical courses, train-the-trainers programmes, and industrial consultancy and services.\n" +
                "\n" +
                "The birth of GMI was the outcome of a joint venture between the Governments of Malaysia and Germany. GMI is governed by a 10-member Board of Directors comprising representatives from both governments, plus public and industrial representatives. The institution was set up as a Company Limited by Guarantee whereby the founders are Majlis Amanah Rakyat (MARA) and the Malaysian German Chamber of Commerce and Industry (MGCC), and its implementing agencies are MARA and Deutsche Gesellschaft für Internationale Zusammenarbeit (GIZ) GmbH (formerly known as German Technical Corporation or GTZ). GMI had its first student enrollment in 1992 and the growing number of enrollment is prevalent."
    var contactUs = "German-Malaysian Institute (199201016476)\n" +
            "Jalan Ilmiah, Taman Universiti,\n" +
            "43000, Kajang, Selangor, Malaysia\n" +
            "Tel : +603-8921 9000 / 9191 / 9046 / 9322\n" +
            "Fax : +603-8921 9001\n" +
            "Email : marketing@gmi.edu.my"
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
                                "Enquiry Section",
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
    ) { innerPadding ->
        // Display content based on the selected page
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = TransparentBlue).verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(text = "About Us", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Color.Black)
            Text(text = aboutUs, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black)
            Text(text = "Contact Information", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Color.Black)
            Text(text = contactUs, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black)
        }
    }
}
