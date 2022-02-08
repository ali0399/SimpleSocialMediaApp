package com.example.smartkeeratest

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.databinding.ActivityChatBinding
import com.example.smartkeeratest.models.FriendxChat
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.viewModels.SsmaViewModel
import com.example.smartkeeratest.viewModels.SsmaViewModelFactory
import com.example.smartkeeratest.views.ChatRecyclerAdapter

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    lateinit var ssmaViewModel: SsmaViewModel
    lateinit var chatRecyclerAdapter: ChatRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var chatList = ArrayList<FriendxChat>()
        val ssmaService = RetrofitHelper.getInstance().create(SsmaApi::class.java)
        val repository = SsmaRepository(ssmaService)
        //initializing View model
        ssmaViewModel =
            ViewModelProvider(this,
                SsmaViewModelFactory(repository, this)).get(SsmaViewModel::class.java)
        //configuring recycler adapter to recycler View
        chatRecyclerAdapter = ChatRecyclerAdapter(this)
        binding.chatRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.chatRv.adapter = chatRecyclerAdapter

        binding.chatSearchView.isClickable = false

        ssmaViewModel.conversationDetails.observe(this, Observer {
            with(chatRecyclerAdapter) {
                binding.chatSearchView.isClickable = true
                chatList.addAll(it.FriendList)
                setChats(it.FriendList)
                notifyDataSetChanged()
            }
        })

        binding.chatSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                chatRecyclerAdapter.filter.filter(newText)
                return false
            }

        })
        binding.chatBackBtn.setOnClickListener { finish() }
    }
}