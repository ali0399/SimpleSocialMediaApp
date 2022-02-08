package com.example.smartkeeratest.models

data class PostsList(
    val Status: Boolean,
    val Message: String,
    val PostList: List<Post>,
    val PageNo: Int,
)