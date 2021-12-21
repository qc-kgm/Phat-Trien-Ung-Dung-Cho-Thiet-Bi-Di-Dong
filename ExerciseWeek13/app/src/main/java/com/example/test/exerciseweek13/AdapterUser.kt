package com.example.test.exerciseweek13

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user_info.view.*

class AdapterUser(val ls : MutableList<UserInfo>) : RecyclerView.Adapter<AdapterUser.UserViewHolder>() {


    class UserViewHolder(val itemVIew : View) : RecyclerView.ViewHolder(itemVIew){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_info,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = ls[position]
        holder.itemView.tvName.text = user.name
        holder.itemView.tvEmail.text = user.email
        Picasso
            .get()
            .load(MainActivity.DOMAIN_API+user.avatar_thumbnail)
            .placeholder(R.drawable.icon_github)
            .into(holder.itemView.ivAvatar)
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,UserActivity::class.java)
            intent.putExtra("data", Gson().toJson(user))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ls.size
    }
}