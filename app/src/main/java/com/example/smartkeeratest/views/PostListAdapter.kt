package com.example.smartkeeratest.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartkeeratest.R
import com.example.smartkeeratest.models.Post

class PostListAdapter(private val context: Context) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private var postList: List<Post>? = null

    fun setPostList(postList: List<Post>) {
        this.postList = postList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PostListAdapter.PostViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)
        return PostListAdapter.PostViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: PostListAdapter.PostViewHolder, position: Int) {
        holder.bind(context, postList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (postList != null) postList?.size!!
        else 0
    }

    class PostViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
        var postImg = view.findViewById<ImageView>(R.id.postImg)
        var postPorfileImg = view.findViewById<ImageView>(R.id.postProfileImg)
        var postname = view.findViewById<TextView>(R.id.postNameTv)
        var postTime = view.findViewById<TextView>(R.id.postTimeTv)
        fun bind(context: Context, data: Post) {
            Glide.with(context).load(data.PostImage).into(postImg)
            postname.text = data.Name
            postTime.text = data.Time
            Glide.with(context).load(data.ProfileImage).into(postPorfileImg)
        }
    }
}