package com.example.test.gmailclone

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_email.view.*
import org.json.JSONArray
import kotlin.random.Random

class AdapterEmail(val listEmail : MutableList<Email>) : RecyclerView.Adapter<AdapterEmail.ViewHolder>() {


    val lsColor= listOf<String>("#00FF7F", "#000080","#90EE90", "#4B0082","#1E90FF","#4682B4","#FFC0CB","#66CDAA")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_email,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val t=listEmail[position]
        holder.itemView.tvSender.text=t.full_name
        holder.itemView.tvTimeReceiving.text=t.time
        holder.itemView.tvTitleMail.text=t.title
        holder.itemView.tvContentMail.text=t.content
        holder.itemView.btnName.text=t.full_name.get(0).toString()
//        holder.itemView.btnName.setBackgroundColor(12)
        randomBackgroundColor(holder.itemView.btnName)
        if(t.favorite){
            holder.itemView.ivFavorite.setImageResource(android.R.drawable.star_big_on)
        }
        else {
            holder.itemView.ivFavorite.setImageResource(android.R.drawable.star_big_off)
        }
        holder.itemView.ivFavorite.setOnClickListener {
            if(t.favorite){
                t.favorite=false
                holder.itemView.ivFavorite.setImageResource(android.R.drawable.star_big_off)
            }
            else {
                t.favorite=true
                holder.itemView.ivFavorite.setImageResource(android.R.drawable.star_big_on)
            }
        }
    }

    override fun getItemCount(): Int {
        return listEmail.size
    }
    fun randomBackgroundColor(v:View){
        val t= Random.nextInt(255)
        v.backgroundTintList= ColorStateList.valueOf(Color.rgb(Random.nextInt(255) ,Random.nextInt(255) ,Random.nextInt(255) ))
//        v.setBackgroundColor( Color.rgb(Random.nextInt(255) ,Random.nextInt(255) ,Random.nextInt(255) ))
    }





    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}