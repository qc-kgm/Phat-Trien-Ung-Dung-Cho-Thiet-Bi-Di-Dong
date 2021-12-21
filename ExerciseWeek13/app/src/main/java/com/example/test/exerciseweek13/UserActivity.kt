package com.example.test.exerciseweek13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*
import org.json.JSONObject

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getStringExtra("data")

        setContentView(R.layout.activity_user)
        if(data != null){
            initView(data)

        }
    }
    fun initView(json : String){
        val o = JSONObject(json)
        tvName.text = o.getString("name")
        tvUsername.text=o.getString("username")
        tvAddress.text=o.getString("address")
        tvPhone.text=o.getString("phone")
        tvEmail.text = o.getString("email")
        tvCompany.text = o.getString("company")
        Picasso.get()
            .load(MainActivity.DOMAIN_API+o.getString("avatar_photo"))
            .into(ivAvatarFull)
    }
}