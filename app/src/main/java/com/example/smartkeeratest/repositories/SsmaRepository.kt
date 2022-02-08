package com.example.smartkeeratest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.models.ConversationDetails
import com.example.smartkeeratest.models.HomePageDetails
import com.example.smartkeeratest.models.PostsList
import com.example.smartkeeratest.models.ProfileDetails

class SsmaRepository(private val ssmaApi: SsmaApi) {

    private val mHomePageDetails = MutableLiveData<HomePageDetails>()
    val homePageDetails: LiveData<HomePageDetails>
        get() {
            return mHomePageDetails
        }

    suspend fun getHomePageDetails(token: String, appVersion: String) {
        val result = ssmaApi.getHomePageDetails(token, appVersion)
        if (result?.body() != null) {
            mHomePageDetails.postValue(result.body())
        }

    }

    private val profilePostList = MutableLiveData<PostsList>()
    val postsList: LiveData<PostsList>
        get() = profilePostList

    suspend fun getPostList(token: String, appVersion: String) {
        val result = ssmaApi.getPosts(token, appVersion)
        if (result?.body() != null) {
            profilePostList.postValue(result.body())
        }
    }

    private val mProfileDetails = MutableLiveData<ProfileDetails>()
    val profileDetails: LiveData<ProfileDetails>
        get() = mProfileDetails

    suspend fun getProfileDetails(token: String, appVersion: String, userId: Int) {
        val result = ssmaApi.getProfileDetails(token, appVersion, userId)
        if (result?.body() != null) {
            mProfileDetails.postValue(result.body())
        }
    }

    private val mConversations = MutableLiveData<ConversationDetails>()
    val conversations: LiveData<ConversationDetails>
        get() = mConversations

    suspend fun getConversations(token: String, appVersion: String) {
        val result = ssmaApi.getConversations(token, appVersion)
        if (result?.body() != null) {
            mConversations.postValue(result.body())
        }
    }
}