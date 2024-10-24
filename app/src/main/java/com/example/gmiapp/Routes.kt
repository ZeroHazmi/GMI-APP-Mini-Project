package com.example.gmiapp

sealed class AppRoute(val route: String) {
    object Splash : AppRoute("splash")
    object HomePage : AppRoute("homepage")
    object Courses : AppRoute("courses")
    object Enquiry : AppRoute("enquiry")
}