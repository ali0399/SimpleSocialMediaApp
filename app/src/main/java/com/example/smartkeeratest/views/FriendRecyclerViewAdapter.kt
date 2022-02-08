package com.example.smartkeeratest.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartkeeratest.R
import com.example.smartkeeratest.models.Friend

class FriendRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<FriendRecyclerViewAdapter.FriendViewHolder>() {

    private var friendList: List<Friend>? = null

    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FriendRecyclerViewAdapter.FriendViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.friend_list_item, parent, false)
        return FriendViewHolder(view, context)
    }

    override fun onBindViewHolder(
        holder: FriendRecyclerViewAdapter.FriendViewHolder,
        position: Int,
    ) {
        holder.bind(friendList?.get(position)!!, context)
    }

    override fun getItemCount(): Int {
        return if (friendList != null) friendList?.size!!
        else 0
    }

    class FriendViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
        var friendImg = view.findViewById<ImageView>(R.id.friendIv)

        fun bind(data: Friend, context: Context) {
            Glide.with(context).load(data.ProfileImage).into(friendImg)
        }
    }
}