package com.animlistingdemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.animlistingdemo.AnimDetailsActivity
import com.animlistingdemo.R
import com.animlistingdemo.data.AnimItem
import com.animlistingdemo.databinding.AnimItemBinding
import com.bumptech.glide.Glide


class AnimListingAdapter(
    private val context: Context,
    private val list: ArrayList<AnimItem>
) : RecyclerView.Adapter<AnimListingAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            AnimItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    class MyViewHolder(binding: AnimItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var posterImage: ImageView = binding.posterImage
        var title: TextView = binding.title
        var numberOfEpisodes: TextView = binding.numberOfEpisodes
        var rating: TextView = binding.rating
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.numberOfEpisodes.text = item.numberOfEpisode.toString()
        holder.rating.text = item.rating

        Glide.with(context)
            .load(item.posterImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.posterImage)

        holder.itemView.setOnClickListener {
            var intent = Intent(context, AnimDetailsActivity::class.java)
            intent.putExtra("animeId", item.animeId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
