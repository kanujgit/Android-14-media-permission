package com.example.android_14_media_permission

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val images: MutableList<ImageDataModel> = ArrayList()

    fun setImages(imageList: List<ImageDataModel>) {
        images.clear()
        images.addAll(imageList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageDataModel = images[position]
        Glide.with(context).load(imageDataModel.uri)
            .placeholder(R.drawable.android_14_logo) // Placeholder image resource
            .error(R.drawable.android_14_logo) // Error image resource
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}