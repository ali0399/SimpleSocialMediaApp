package com.example.smartkeeratest.models

data class HomePageDetails(
    val Status: Boolean,
    val Message: String,
    val UserId: Int,
    val Name: String,
    val ProfileImage: String,
    val FriendList: List<Friend>,
    val PageNo: Int,
)