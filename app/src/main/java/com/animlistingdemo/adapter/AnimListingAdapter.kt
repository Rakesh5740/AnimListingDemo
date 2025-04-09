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
import com.animlistingdemo.data.Employee
import com.animlistingdemo.databinding.AnimItemBinding
import com.bumptech.glide.Glide


class AnimListingAdapter(
    private val context: Context,
    private val list: ArrayList<Employee>
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
        var thumbNail: ImageView = binding.profileImage
        var fullName: TextView = binding.fullName
        var email: TextView = binding.email
        var team: TextView = binding.team
        var jobTitle: TextView = binding.jobTitle
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.fullName.text = item.fullName
        holder.email.text = item.email
        holder.team.text = item.team
        holder.jobTitle.text = item.jobTitle

        Glide.with(context)
            .load(item.thumbNail)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.thumbNail)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, AnimDetailsActivity::class.java))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}
