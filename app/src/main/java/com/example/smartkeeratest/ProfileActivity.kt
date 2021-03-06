package com.example.smartkeeratest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.databinding.ActivityProfileBinding
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.viewModels.SsmaViewModel
import com.example.smartkeeratest.viewModels.SsmaViewModelFactory
import com.example.smartkeeratest.views.PhotoRecyclerAdapter

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    lateinit var ssmaViewModel: SsmaViewModel
    lateinit var photoRecyclerAdapter: PhotoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profileShimmer.startShimmerAnimation()

        val ssmaService = RetrofitHelper.getInstance().create(SsmaApi::class.java)
        val repository = SsmaRepository(ssmaService)

        val myPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        //initializing View model
        ssmaViewModel =
            ViewModelProvider(this,
                SsmaViewModelFactory(repository, this)).get(SsmaViewModel::class.java)
        //configuring recycler adapter to recycler View
        photoRecyclerAdapter = PhotoRecyclerAdapter(this)
        binding.photoRv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.photoRv.adapter = photoRecyclerAdapter

        ssmaViewModel.profileDetails.observe(this, Observer {
            with(binding) {
                profileShimmer.stopShimmerAnimation()
                profileShimmer.visibility = View.GONE
                profileDetailsContainer.visibility = View.VISIBLE
                photoRv.visibility = View.VISIBLE

                if (it.ProfileImage == null) {
                    binding.profileImgIv.visibility = View.GONE
                    binding.profileImgTv.visibility = View.VISIBLE
                    binding.profileImgTv.text = it.Name[0].toString()
                } else {
                    binding.profileImgIv.visibility = View.VISIBLE
                    binding.profileImgTv.visibility = View.GONE
                    Glide.with(this@ProfileActivity).load(it.ProfileImage)
                        .into(binding.profileImgIv)
                }

                profileNameTv.text = it.Name
                profileDescTv.text = it.Description
                profileLocTv.text = it.Location
                photoCountTv.text = it.PhotosCount.toString()
                followerCountTv.text = it.FollowersCount.toString()
                followsCountTv.text = it.FollowsCount.toString()
                photoRecyclerAdapter.setPhotoList(it.PhotoList)
                photoRecyclerAdapter.notifyDataSetChanged()
            }
        })

        binding.toChatBtn.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
        binding.profileBackBtn.setOnClickListener {
            finish()
        }
    }
}