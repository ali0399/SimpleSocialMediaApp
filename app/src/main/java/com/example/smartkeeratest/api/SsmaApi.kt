package com.example.smartkeeratest.api

import com.example.smartkeeratest.models.ConversationDetails
import com.example.smartkeeratest.models.HomePageDetails
import com.example.smartkeeratest.models.PostsList
import com.example.smartkeeratest.models.ProfileDetails
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SsmaApi {
    @POST("demoapi/demo/main")  //https://app.smartkeeda.com/demoapi/demo/main
    suspend fun getHomePageDetails(
        @Header("Token") token: String,
        @Header("AppVersion") appVersion: String,
    ): Response<HomePageDetails>

    @POST("demoapi/demo/PostData")
    suspend fun getPosts(
        @Header("Token") token: String,
        @Header("AppVersion") appVersion: String,
    ): Response<PostsList>

    @POST("demoapi/demo/Getprofile")
    suspend fun getProfileDetails(
        @Header("Token") token: String,
        @Header("AppVersion") appVersion: String,
        @Query("UserId") userId: Int,
    ): Response<ProfileDetails>

    @POST("demoapi/demo/GetConversation")
    suspend fun getConversations(
        @Header("Token") token: String,
        @Header("AppVersion") appVersion: String,
    ): Response<ConversationDetails>
}