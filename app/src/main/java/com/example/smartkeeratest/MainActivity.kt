package com.example.smartkeeratest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.databinding.ActivityMainBinding
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.util.Constants
import com.example.smartkeeratest.viewModels.SsmaViewModel
import com.example.smartkeeratest.viewModels.SsmaViewModelFactory
import com.example.smartkeeratest.views.FriendRecyclerViewAdapter
import com.example.smartkeeratest.views.PostListAdapter

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var ssmaViewModel: SsmaViewModel
    lateinit var friendRecyclerAdapter: FriendRecyclerViewAdapter
    lateinit var postListAdapter: PostListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.homeShimmer.startShimmerAnimation()

        val myPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        val ssmaService = RetrofitHelper.getInstance().create(SsmaApi::class.java)
        val repository = SsmaRepository(ssmaService)

        //initializing View model
        ssmaViewModel =
            ViewModelProvider(this,
                SsmaViewModelFactory(repository, this)).get(SsmaViewModel::class.java)

        //configuring recycler adapter
        binding.friendRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        friendRecyclerAdapter = FriendRecyclerViewAdapter(this)
        binding.friendRecyclerView.adapter = friendRecyclerAdapter

        ssmaViewModel.userDetails.observe(this, Observer {
//            Log.d(TAG, "onCreate: name: ${it.Name}, Friends: ${it.FriendList.toString()}")
            binding.homeShimmer.stopShimmerAnimation()
            binding.homeShimmer.visibility = View.GONE
            binding.homeContainer.visibility = View.VISIBLE

            friendRecyclerAdapter.setFriendList(it.FriendList)
            friendRecyclerAdapter.notifyDataSetChanged()
            binding.nameTv.text = it.Name + "!"

            myPref.edit().putInt(Constants.USER_ID, it.UserId).apply()

            if (it.ProfileImage == null) {
                binding.profileImgIv.visibility = View.GONE
                binding.profileImgTv.visibility = View.VISIBLE
                binding.profileImgTv.text = it.Name[0].toString()
            } else {
                binding.profileImgIv.visibility = View.VISIBLE
                binding.profileImgTv.visibility = View.GONE
                Glide.with(this).load(it.ProfileImage).into(binding.profileImgIv)
            }
        })

        val postListPager = findViewById<ViewPager2>(R.id.postPager)
        postListAdapter = PostListAdapter(this)
        postListPager.adapter = postListAdapter
        ssmaViewModel.postList.observe(this, Observer {
            Log.d(TAG, "onCreate: observing postList: $it")
            postListAdapter.setPostList(it.PostList)
            postListAdapter.notifyDataSetChanged()
        })

        //go to next activity on click
        binding.profileImgContainer.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}