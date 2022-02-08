package com.example.smartkeeratest.viewModels

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartkeeratest.models.ConversationDetails
import com.example.smartkeeratest.models.HomePageDetails
import com.example.smartkeeratest.models.PostsList
import com.example.smartkeeratest.models.ProfileDetails
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.util.Constants
import com.example.smartkeeratest.util.Constants.APP_VERSION
import com.example.smartkeeratest.util.Constants.TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SsmaViewModel(private val repository: SsmaRepository, context: Context) : ViewModel() {

    init {
        val myPref = context.getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val USER_ID = myPref.getInt(Constants.USER_ID, 1)

        viewModelScope.launch(Dispatchers.IO) {
            repository.getHomePageDetails(TOKEN, APP_VERSION)
            repository.getPostList(TOKEN, APP_VERSION)
            repository.getProfileDetails(TOKEN, APP_VERSION, USER_ID)
            repository.getConversations(TOKEN, APP_VERSION)
        }
    }

    val userDetails: LiveData<HomePageDetails>
        get() = repository.homePageDetails

    val postList: LiveData<PostsList>
        get() = repository.postsList

    val profileDetails: LiveData<ProfileDetails>
        get() = repository.profileDetails

    val conversationDetails: LiveData<ConversationDetails>
        get() = repository.conversations
}