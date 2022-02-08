package com.example.smartkeeratest.models

data class ProfileDetails(
    val Status: Boolean,
    val Message: String,
    val UserId: Int,
    val Name: String,
    val Location: String,
    val Description: String,
    val ProfileImage: String,
    val PhotosCount: Int,
    val FollowersCount: Int,
    val FollowsCount: Int,
    val PhotoList: List<Photo>,
)