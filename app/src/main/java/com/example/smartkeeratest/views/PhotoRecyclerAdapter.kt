package com.example.smartkeeratest.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartkeeratest.R
import com.example.smartkeeratest.models.Photo

class PhotoRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoViewHolder>() {

    private var photoList: List<Photo>? = null

    fun setPhotoList(photoList: List<Photo>) {
        this.photoList = photoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view, context)
    }

    override fun onBindViewHolder(holderPhoto: PhotoViewHolder, position: Int) {
        holderPhoto.bind(context, photoList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (photoList != null) photoList?.size!!
        else 0
    }

    class PhotoViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
        var photoItem = view.findViewById<ImageView>(R.id.photoIv)
        fun bind(context: Context, data: Photo) {
            Glide.with(context).load(data.Image).into(photoItem)
        }
    }
}